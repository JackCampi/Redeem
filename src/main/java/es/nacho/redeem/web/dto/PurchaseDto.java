package es.nacho.redeem.web.dto;

import java.util.Collection;

public class PurchaseDto {

    private String employee;
    private Long value;
    private Collection<Long> productsId;

    public PurchaseDto(String employee, Long value, Collection<Long> productsId) {
        this.employee = employee;
        this.value = value;
        this.productsId = productsId;
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

    public Collection<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(Collection<Long> productsId) {
        this.productsId = productsId;
    }
}
