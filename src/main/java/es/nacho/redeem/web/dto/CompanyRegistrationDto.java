package es.nacho.redeem.web.dto;

public class CompanyRegistrationDto {

    private Long id;
    private String name;
    private Long budget;
    private String areas;

    public CompanyRegistrationDto(Long id, String name, Long budget, String areas) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.areas = areas;
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

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

}
