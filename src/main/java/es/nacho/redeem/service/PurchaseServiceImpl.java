package es.nacho.redeem.service;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Product;
import es.nacho.redeem.model.PurchaseHasProduct;
import es.nacho.redeem.repository.PurchaseRepository;
import es.nacho.redeem.web.dto.transfer.history.EPurchases;
import es.nacho.redeem.web.dto.transfer.history.EmpDto;
import es.nacho.redeem.web.dto.transfer.history.ProductInfoDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public SortedList<EmpDto> getEmployeePurchases(Employee employee, SortedList<EmpDto> sortedList) {

        employee.getPurchases().forEach(purchase -> {

            long amount = 0L;
            ArrayList<ProductInfoDto> productInfoDtos = new ArrayList<>();

            for(PurchaseHasProduct purchaseHasProduct : purchase.getPurchaseHasProducts()){
                Product product = purchaseHasProduct.getProduct();
                amount += product.getPrice();
                /*productInfoDtos.add(new ProductInfoDto(
                        product.getName(),
                        purchaseHasProduct.getQuantity(),
                        product.getPrice()
                ));*/
            }

        });


        return null;
    }
}
