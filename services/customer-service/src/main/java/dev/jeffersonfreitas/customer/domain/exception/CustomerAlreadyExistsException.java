package dev.jeffersonfreitas.customer.domain.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(String msg){
        super(msg);
    }
}
