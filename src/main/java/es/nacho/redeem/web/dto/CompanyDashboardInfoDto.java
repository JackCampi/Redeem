package es.nacho.redeem.web.dto;

public class CompanyDashboardInfoDto {

    private String name;
    private long nit;

    public CompanyDashboardInfoDto(String name, long nit) {
        this.name = name;
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }
}
