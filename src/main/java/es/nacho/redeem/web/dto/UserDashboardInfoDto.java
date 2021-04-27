package es.nacho.redeem.web.dto;

public class UserDashboardInfoDto {

    private String name;
    private long balance;

    public UserDashboardInfoDto(String completeName, long balance) {
        this.name = completeName;
        this.balance = balance;
    }

    public String getCompleteName() {
        return name;
    }

    public void setCompleteName(String completeName) {
        this.name = completeName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
