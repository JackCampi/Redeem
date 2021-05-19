package es.nacho.redeem.web.dto;

import java.util.Collection;

public class AllocationDto {
    
    private String employee;
    private Long amount;
    private Collection<String> areas;
    
    public AllocationDto(String employee, Long amount) {
        this.employee = employee;
        this.amount = amount;
    }

    public AllocationDto(Collection<String> areas, Long amount) {
        this.areas = areas;
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

    public Collection<String> getAreas() {
        return areas;
    }

    public void setAreas(Collection<String> areas) {
        this.areas = areas;
    }

}
