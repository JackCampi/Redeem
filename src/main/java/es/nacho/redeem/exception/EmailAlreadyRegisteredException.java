package es.nacho.redeem.exception;

public class EmailAlreadyRegisteredException extends Exception{

    public EmailAlreadyRegisteredException() {
        super("The email is already registered");
    }
}
