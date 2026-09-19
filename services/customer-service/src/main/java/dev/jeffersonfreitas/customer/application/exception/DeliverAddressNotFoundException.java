package dev.jeffersonfreitas.customer.application.exception;

public class DeliverAddressNotFoundException extends RuntimeException{

    public DeliverAddressNotFoundException(String msg){
        super(msg);
    }
}
