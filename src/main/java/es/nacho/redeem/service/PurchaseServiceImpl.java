package es.nacho.redeem.service;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.exception.CompanyNotFoundException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.format.CalendarFormat;
import es.nacho.redeem.model.*;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.repository.PurchaseRepository;
import es.nacho.redeem.web.dto.transfer.history.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        areaRepository.findByCompany(company).forEach(area -> {

            area.getEmployees().forEach(employee -> {

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

                    sortedList.add(new APurchases(
                            "APurchases",
                            amount,
                            CalendarFormat.formatLocalDateTime(purchase.getDateTime()),
                            productInfoDtos,
                            employee.getName()
                            ));

                });

            });

        });



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
