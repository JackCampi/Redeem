package es.nacho.redeem.web.dto.employee;

public class MemberDto {

    private long id;
    private String name;
    private String lastName;
    private String email;
    private String oldEmail;
    private String cellphone;
    private String area;
    private String birthday;
    private long balance;

    public MemberDto(long id, String name, String lastName, String email, String oldEmail, String cellphone, String area, String birthday, long balance) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.oldEmail = oldEmail;
        this.cellphone = cellphone;
        this.area = area;
        this.birthday = birthday;
        this.balance = balance;
    }

    public MemberDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }
}
