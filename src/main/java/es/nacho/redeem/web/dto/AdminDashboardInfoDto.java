package es.nacho.redeem.web.dto;

public class AdminDashboardInfoDto {

    private String name;
    private String companyName;
    private long companyNit;
    private long companyBudget;

    public AdminDashboardInfoDto(String name, String companyName, long companyNit, long companyBudget) {
        this.name = name;
        this.companyName = companyName;
        this.companyNit = companyNit;
        this.companyBudget = companyBudget;
    }

    public AdminDashboardInfoDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getCompanyNit() {
        return companyNit;
    }

    public void setCompanyNit(long companyNit) {
        this.companyNit = companyNit;
    }

    public long getCompanyBudget() {
        return companyBudget;
    }

    public void setCompanyBudget(long companyBudget) {
        this.companyBudget = companyBudget;
    }
}
