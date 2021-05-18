package es.nacho.redeem.web.dto;

public class AllocationDto {
    
    private String employee;
    private Long amount;
    
    public AllocationDto(String employee, Long amount) {
        this.employee = employee;
        this.amount = amount;
    }

    public AllocationDto() {
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
