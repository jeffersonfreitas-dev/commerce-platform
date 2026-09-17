package dev.jeffersonfreitas.customer.application.exception;

public class NullObjectException extends RuntimeException{

    public NullObjectException(String msg){
        super(msg);
    }
}
