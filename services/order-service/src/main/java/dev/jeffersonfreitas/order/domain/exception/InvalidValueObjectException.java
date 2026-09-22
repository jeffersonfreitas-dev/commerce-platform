package dev.jeffersonfreitas.order.domain.exception;

public class InvalidValueObjectException extends RuntimeException{

    public InvalidValueObjectException(String msg){
        super(msg);
    }
}
