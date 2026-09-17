package dev.jeffersonfreitas.customer.application.exception;

public class AddressNotBelongException extends RuntimeException{

    public AddressNotBelongException(String msg){
        super(msg);
    }
}
