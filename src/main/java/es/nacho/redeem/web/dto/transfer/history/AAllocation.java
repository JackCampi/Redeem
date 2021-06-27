package es.nacho.redeem.web.dto.transfer.history;

public class AAllocation extends TransHistoryDto implements AdminDto{

    private String allocationTo;
    private String madeBy;
    private String description;

    public AAllocation(String type, long amount, String datetime, String allocationTo, String madeBy, String description) {
        super(type, amount, datetime);
        this.allocationTo = allocationTo;
        this.madeBy = madeBy;
        this.description = description;
    }

    public String getAllocationTo() {
        return allocationTo;
    }

    public void setAllocationTo(String allocationTo) {
        this.allocationTo = allocationTo;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AAllocation() {
        super("",0L, "");
    }

    @Override
    public String getDateTime() {
        return super.getDatetime();
    }

    @Override
    public int compareTo(AdminDto o) {
        return this.getDatetime().compareTo(o.getDateTime());
    }
}
