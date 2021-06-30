package es.nacho.redeem.model;

import es.nacho.redeem.model.compositeKeys.PurchaseHasProductKey;

import javax.persistence.*;

@Entity
@Table(name = "purchase_has_product")
public class PurchaseHasProduct {

    @EmbeddedId
    private PurchaseHasProductKey id;

    @ManyToOne
    @MapsId("pur_id")
    @JoinColumn(name = "purchase_pur_id")
    Purchase purchase;

    @ManyToOne
    @MapsId("prod_id")
    @JoinColumn(name = "product_prod_id")
    Product product;

    @Column(name = "php_quantity", nullable = false)
    private int quantity;

    public PurchaseHasProduct(PurchaseHasProductKey id, Purchase purchase, Product product, int quantity) {
        this.id = id;
        this.purchase = purchase;
        this.product = product;
        this.quantity = quantity;
    }

    public PurchaseHasProduct() {
    }

    public PurchaseHasProductKey getId() {
        return id;
    }

    public void setId(PurchaseHasProductKey id) {
        this.id = id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
