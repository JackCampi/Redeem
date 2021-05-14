package es.nacho.redeem.web.dto;

public class TransferDto {

    String receiverIdentifier;
    long amount;

    public TransferDto(String receiverIdentifier, long amount) {
        this.receiverIdentifier = receiverIdentifier;
        this.amount = amount;
    }

    public TransferDto() {
    }

    public String getReceiverIdentifier() {
        return receiverIdentifier;
    }

    public void setReceiverIdentifier(String receiverIdentifier) {
        this.receiverIdentifier = receiverIdentifier;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
