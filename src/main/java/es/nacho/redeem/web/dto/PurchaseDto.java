package es.nacho.redeem.web.dto;

public class PurchaseDto {

    private Long employeeId;
    private Long value;
    private String productsAndQuantities;

    public PurchaseDto(Long employeeId, Long value, String productsAndQuantities) {
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

    public String getProductsAndQuantities() {
        return productsAndQuantities;
    }

    public void setProductsAndQuantities(String productsAndQuantities) {
        this.productsAndQuantities = productsAndQuantities;
    }
}
