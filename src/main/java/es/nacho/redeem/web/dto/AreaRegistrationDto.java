package es.nacho.redeem.web.dto;

public class AreaRegistrationDto {

    private String name;

    public AreaRegistrationDto(String name) {
        this.name = name;
    }

    public AreaRegistrationDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
