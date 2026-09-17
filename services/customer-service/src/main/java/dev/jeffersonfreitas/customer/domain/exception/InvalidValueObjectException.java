package dev.jeffersonfreitas.customer.domain.exception;

public class InvalidValueObjectException extends RuntimeException{

    public InvalidValueObjectException(String msg){
        super(msg);
    }
}
