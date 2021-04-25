package es.nacho.redeem.web.dto;

public class CompanyRegistrationDto {

    private String name;
    private Long budget;

    public CompanyRegistrationDto(String name, Long budget) {
        this.name = name;
        this.budget = budget;
    }

    public CompanyRegistrationDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }
}
