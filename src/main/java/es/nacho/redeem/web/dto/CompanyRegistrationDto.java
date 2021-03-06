package es.nacho.redeem.web.dto;

public class CompanyRegistrationDto {

    private Long id;
    private String name;
    private String areas;


    public CompanyRegistrationDto(Long id, String name, String areas) {
        this.id = id;
        this.name = name;
        this.areas = areas;
    }

    public CompanyRegistrationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

}
