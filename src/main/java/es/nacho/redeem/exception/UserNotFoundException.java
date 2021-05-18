package es.nacho.redeem.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
