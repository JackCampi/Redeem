package es.nacho.redeem.exception;

public class OnlyAdminRemainingException extends RuntimeException{

    public OnlyAdminRemainingException() {
        super("You cannot modify the last administrator remaining");
    }
}