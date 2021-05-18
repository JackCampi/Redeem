package es.nacho.redeem.config.dto;

public class AuthDto {

    private String email;
    private String name;
    private long id;

    public AuthDto(String email, String name, long id) {
        this.email = email;
        this.name = name;
        this.id = id;
    }

    public AuthDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
