package es.nacho.redeem.service;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.exception.*;
import es.nacho.redeem.format.CalendarFormat;
import es.nacho.redeem.model.*;
import es.nacho.redeem.model.compositeKeys.PurchaseHasProductKey;
import es.nacho.redeem.repository.*;
import es.nacho.redeem.transaction.BalanceTransaction;
import es.nacho.redeem.web.dto.transfer.history.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseHasProductRepository purchaseHasProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private BalanceTransaction balanceTransaction;

    @Override
    @Transactional(rollbackOn = {ProductNotFoundException.class, InsufficientStockException.class, InsufficientBalanceException.class})
    public void accomplishPurchase(Employee employee, Collection<ArrayList<Long>> productsAndQuantities, Long purchaseValue)
        throws ProductNotFoundException, InsufficientStockException, InsufficientBalanceException{

        Purchase purchase = new Purchase(LocalDateTime.now(), employee, purchaseValue);
        purchaseRepository.save(purchase);

        Collection<PurchaseHasProduct> purchasesHaveProducts = new ArrayList<>();

        for (ArrayList<Long> productAndQuantity : productsAndQuantities) {
            Optional<Product> OpProduct = productRepository.findById(productAndQuantity.get(0));
            int quantity = productAndQuantity.get(1).intValue();

            if(!OpProduct.isPresent()) throw new ProductNotFoundException();
            Product product = OpProduct.get();

            product = productService.reduceProductStock(product, quantity);

            purchasesHaveProducts.add(new PurchaseHasProduct(
                    new PurchaseHasProductKey(purchase.getId(), product.getId()),
                    purchase, product, quantity));

            purchasesHaveProducts.forEach(purchaseHasProduct -> purchaseHasProductRepository.save(purchaseHasProduct));
        }
        balanceTransaction.returnBalanceToCompany(employee.getArea().getCompany().getId(),employee.getId(),purchaseValue);
        employeeRepository.save(employee);
    }

    @Override
    public SortedList<EmpDto> getEmployeePurchases(Employee employee, SortedList<EmpDto> sortedList) {

        employee.getPurchases().forEach(purchase -> {

            long amount = 0L;
            ArrayList<ProductInfoDto> productInfoDtos = new ArrayList<>();

            for(PurchaseHasProduct purchaseHasProduct : purchase.getPurchaseHasProducts()){
                Product product = purchaseHasProduct.getProduct();
                amount += product.getPrice();
                productInfoDtos.add(new ProductInfoDto(
                        product.getName(),
                        purchaseHasProduct.getQuantity(),
                        product.getPrice()
                ));
            }

            sortedList.add(new EPurchases(
                    "EPurchases",
                    amount,
                    CalendarFormat.formatLocalDateTime(purchase.getDateTime()),
                    productInfoDtos
            ));
        });
        return sortedList;
    }

    @Override
    public SortedList<AdminDto> getAdminPurchases(long nit, SortedList<AdminDto> sortedList) {

        Optional<Company> companyOptional = companyRepository.findById(nit);
        if(!companyOptional.isPresent()) throw new CompanyNotFoundException();
        Company company = companyOptional.get();

        areaRepository.findByCompany(company).forEach(area -> area.getEmployees().forEach(employee -> employee.getPurchases().forEach(purchase -> {

            long amount = 0L;
            ArrayList<ProductInfoDto> productInfoDtos = new ArrayList<>();

            for(PurchaseHasProduct purchaseHasProduct : purchase.getPurchaseHasProducts()){
                Product product = purchaseHasProduct.getProduct();
                amount += product.getPrice();
                productInfoDtos.add(new ProductInfoDto(
                        product.getName(),
                        purchaseHasProduct.getQuantity(),
                        product.getPrice()
                ));
            }

            sortedList.add(new APurchases(
                    "APurchases",
                    amount,
                    CalendarFormat.formatLocalDateTime(purchase.getDateTime()),
                    productInfoDtos,
                    employee.getName()
                    ));
        })));
        return sortedList;
    }

    public Collection<Purchase> getPurchasesToSend(Long nit){

        Collection<Purchase> purchasesToSend = new ArrayList<>();
        employeeRepository.findAllByCompanyAndRol(nit,"empleado").forEach(employee ->
                employee.getPurchases().forEach(purchase ->
                {
                    if (!purchase.getIsSent()) {
                        purchasesToSend.add(purchase);
                    }
                })
        );
        return purchasesToSend;
    }

    @Override
    public void setSentPurchase(Long purchaseId) throws Exception {
        Optional<Purchase> possiblePurchase = purchaseRepository.findById(purchaseId);
        if(!possiblePurchase.isPresent()) throw new Exception();
        Purchase purchase = possiblePurchase.get();

        purchase.setIsSent(true);
        purchaseRepository.save(purchase);
    }
}
