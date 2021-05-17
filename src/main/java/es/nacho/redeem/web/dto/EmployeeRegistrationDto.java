package es.nacho.redeem.web.dto;

public class EmployeeRegistrationDto {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String cellphone;
    private String birthday;
    private String area;
    private String passwordConfirm;

    public EmployeeRegistrationDto(String name, String lastName, String email, String password, String cellphone, String birthday, String area, String passwordConfirm) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.birthday = birthday;
        this.area = area;
        this.passwordConfirm = passwordConfirm;
    }

    public EmployeeRegistrationDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
