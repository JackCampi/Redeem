package es.nacho.redeem.web.dto.transfer.history;

public class EAllocation extends TransHistoryDto implements EmpDto{

    private String madeBy;

    public EAllocation(long amount, String datetime, String madeBy) {
        super(amount, datetime);
        this.madeBy = madeBy;
    }
    public EAllocation() {
        super(0L, "");
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
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
