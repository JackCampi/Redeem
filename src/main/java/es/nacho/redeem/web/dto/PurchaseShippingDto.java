package es.nacho.redeem.web.dto;

public class PurchaseShippingDto {

    private Long purchaseId;

    public PurchaseShippingDto(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
}
