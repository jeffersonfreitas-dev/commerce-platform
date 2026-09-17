package dev.jeffersonfreitas.customer.application.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
