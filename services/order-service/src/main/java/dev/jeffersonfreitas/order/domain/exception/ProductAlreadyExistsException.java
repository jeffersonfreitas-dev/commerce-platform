package dev.jeffersonfreitas.order.domain.exception;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(String msg){
        super(msg);
    }
}
