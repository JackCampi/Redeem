package es.nacho.redeem.model.compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

//Si se preguntan el porque de esta clase, aquí se explica https://www.baeldung.com/jpa-many-to-many

@Embeddable
public class PurchaseHasProductKey implements Serializable {

    @Column(name = "purchase_pur_id")
    private Long purchaseId;

    @Column(name = "product_prod_id")
    private Long productId;

    public PurchaseHasProductKey(Long purchaseId, Long productId) {
        this.purchaseId = purchaseId;
        this.productId = productId;
    }

    public PurchaseHasProductKey() {
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
