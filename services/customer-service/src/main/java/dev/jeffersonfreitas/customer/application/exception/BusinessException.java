package dev.jeffersonfreitas.customer.application.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String msg){
        super(msg);
    }
}
