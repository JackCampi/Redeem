package es.nacho.redeem.web.dto;

import java.util.Calendar;

public class EmployeeRegistrationDto {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String cellphone;
    private Calendar birthday;
    private Long balance;
    private Boolean active;
    private String rol;

    public EmployeeRegistrationDto(String name, String lastName, String email, String password, String cellphone, Calendar birthday, Long balance, Boolean active, String rol) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.birthday = birthday;
        this.balance = balance;
        this.active = active;
        this.rol = rol;
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

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
