package es.nacho.redeem.web.dto;

import java.util.ArrayList;
import java.util.Collection;

public class PurchaseDto {

    private String employee;
    private Long value;
    private Collection<ArrayList<Long>> productsAndQuantities;

    public PurchaseDto(String employee, Long value, Collection<ArrayList<Long>> productsAndQuantities) {
        this.employee = employee;
        this.value = value;
        this.productsAndQuantities = productsAndQuantities;
    }

    public PurchaseDto() {
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Collection<ArrayList<Long>> getProductsAndQuantities() {
        return productsAndQuantities;
    }

    public void setProductsAndQuantities(Collection<ArrayList<Long>> productsAndQuantities) {
        this.productsAndQuantities = productsAndQuantities;
    }
}
