package es.nacho.redeem.web.dto.transfer.history;

public class TransHistoryDto {

    private long amount;
    private String datetime;

    public TransHistoryDto(long amount, String datetime) {
        this.amount = amount;
        this.datetime = datetime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
