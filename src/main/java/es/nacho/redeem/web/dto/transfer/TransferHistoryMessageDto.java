package es.nacho.redeem.web.dto.transfer;

import java.time.LocalDateTime;
import java.util.Objects;

public class TransferHistoryMessageDto implements Comparable<TransferHistoryMessageDto> {

    private String name;
    private String dateTime;
    private long amount;
    private long isArrival;

    public TransferHistoryMessageDto(String name, String dateTime, long amount, long isArrival) {
        this.name = name;
        this.dateTime = dateTime;
        this.amount = amount;
        this.isArrival = isArrival;
    }

    public TransferHistoryMessageDto() {
    }

    @Override
    public int compareTo(TransferHistoryMessageDto o) {

        /*if(this.dateTime.compareTo(o.getDateTime()) >= 1) return -1;

        else if(this.dateTime.compareTo(o.getDateTime()) <= -1) return 1;

        return 0;*/

        return this.getDateTime().compareTo(o.getDateTime()) * -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferHistoryMessageDto that = (TransferHistoryMessageDto) o;
        return amount == that.amount && isArrival == that.isArrival && Objects.equals(name, that.name) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateTime, amount, isArrival);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getIsArrival() { return isArrival; }

    public void setIsArrival(long isArrival) { this.isArrival = isArrival; }
}
