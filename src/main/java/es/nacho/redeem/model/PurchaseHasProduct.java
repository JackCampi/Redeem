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
    private Long quantity;


}
