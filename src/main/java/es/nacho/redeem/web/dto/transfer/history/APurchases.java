package es.nacho.redeem.web.dto.transfer.history;

import java.util.ArrayList;

public class APurchases extends TransHistoryDto implements AdminDto{

    private ArrayList<ProductInfoDto> productList;
    private String madeBy;

    public APurchases(String type, long amount, String datetime, ArrayList<ProductInfoDto> productList, String madeBy) {
        super(type, amount, datetime);
        this.productList = productList;
        this.madeBy = madeBy;
    }

    public APurchases() {
        super("",0L, "");
    }

    public ArrayList<ProductInfoDto> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductInfoDto> productList) {
        this.productList = productList;
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
    public int compareTo(AdminDto o) {
        return this.getDatetime().compareTo(o.getDateTime());
    }
}
