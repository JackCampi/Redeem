package es.nacho.redeem.web.dto.transfer.history;

public class ETransfer extends TransHistoryDto implements EmpDto{

    private byte isArrival;
    private String name;

    public ETransfer(String type, long amount, String datetime, byte isArrival, String name) {
        super(type, amount, datetime);
        this.isArrival = isArrival;
        this.name = name;
    }

    public ETransfer() {
        super("",0L, "");
    }

    public byte isArrival() {
        return isArrival;
    }

    public byte getIsArrival() {
        return isArrival;
    }

    public void setIsArrival(byte isArrival) {
        this.isArrival = isArrival;
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
        return this.getDatetime().compareTo(o.getDateTime());
    }
}
