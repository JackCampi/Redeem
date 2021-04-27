package es.nacho.redeem.web.dto;

public class UserDashboardInfoDto {

    private String completeName;
    private long balance;

    public UserDashboardInfoDto(String completeName, long balance) {
        this.completeName = completeName;
        this.balance = balance;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
