package es.nacho.redeem.web.dto.transfer.history;

public class TransHistoryDto {

    private String type;
    private long amount;
    private String datetime;

    public TransHistoryDto(String type, long amount, String datetime) {
        this.type = type;
        this.amount = amount;
        this.datetime = datetime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
