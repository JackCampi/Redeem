package es.nacho.redeem.exception;

public class CompanyNotFoundException extends RuntimeException{

    public CompanyNotFoundException() {
        super("The company nit is not aviable");
    }
}
