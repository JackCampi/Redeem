package es.nacho.redeem.web.dto.transfer.history;

import java.util.ArrayList;

public class EPurchases extends TransHistoryDto implements EmpDto{

    private ArrayList<ProductInfoDto> productList;

    public EPurchases() {
        super(0L, "");

    }

    public EPurchases(long amount, String datetime, ArrayList<ProductInfoDto> productList) {
        super(amount, datetime);
        this.productList = productList;
    }

    public ArrayList<ProductInfoDto> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductInfoDto> productList) {
        this.productList = productList;
    }

    @Override
    public int compareTo(EmpDto o) {

        return this.getDatetime().compareTo(o.getDateTime()) * -1;
    }

    @Override
    public String getDateTime() {
        return super.getDatetime();
    }
}
