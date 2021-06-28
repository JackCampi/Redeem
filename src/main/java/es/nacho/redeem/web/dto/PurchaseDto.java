package es.nacho.redeem.web.dto;

import java.util.ArrayList;
import java.util.Collection;

public class PurchaseDto {

    private Long employeeId;
    private Long value;
    private Collection<ArrayList<Long>> productsAndQuantities;

    public PurchaseDto(Long employeeId, Long value, Collection<ArrayList<Long>> productsAndQuantities) {
        this.employeeId = employeeId;
        this.value = value;
        this.productsAndQuantities = productsAndQuantities;
    }

    public PurchaseDto() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
