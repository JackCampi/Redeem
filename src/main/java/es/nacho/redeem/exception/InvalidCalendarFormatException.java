package es.nacho.redeem.exception;

public class InvalidCalendarFormatException extends Exception {
    @Override
    public String getMessage() {
        return "Calendar as a string does not match with the valid format";
    }
}
