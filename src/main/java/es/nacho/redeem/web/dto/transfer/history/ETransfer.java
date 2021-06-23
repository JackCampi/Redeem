package es.nacho.redeem.web.dto.transfer.history;

public class ETransfer extends TransHistoryDto implements EmpDto{

    private byte isArrival;
    private String name;

    public ETransfer(long amount, String datetime, byte isArrival, String name) {
        super(amount, datetime);
        this.isArrival = isArrival;
        this.name = name;
    }

    public ETransfer() {
        super(0L, "");
    }

    public byte isArrival() {
        return isArrival;
    }

    public void setArrival(byte arrival) {
        isArrival = arrival;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDateTime() {
        return super.getDatetime();
    }

    @Override
    public int compareTo(EmpDto o) {
        return this.getDatetime().compareTo(o.getDateTime()) * -1;
    }
}
