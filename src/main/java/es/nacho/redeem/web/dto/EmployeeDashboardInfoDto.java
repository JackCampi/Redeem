package es.nacho.redeem.web.dto;

public class EmployeeDashboardInfoDto {

    private String name;
    private long balance;
    private String companyName;
    private long companyNit;

    public EmployeeDashboardInfoDto(String name, long balance, String companyName, long companyNit) {
        this.name = name;
        this.balance = balance;
        this.companyName = companyName;
        this.companyNit = companyNit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
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
}
