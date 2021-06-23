package es.nacho.redeem.web.dto.transfer.history;

public class ProductInfoDto {

    private String name;
    private int amount;
    private double cost;

    public ProductInfoDto(String name, int amount, double cost) {
        this.name = name;
        this.amount = amount;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
