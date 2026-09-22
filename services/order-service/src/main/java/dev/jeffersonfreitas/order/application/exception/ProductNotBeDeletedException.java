package dev.jeffersonfreitas.order.application.exception;

public class ProductNotBeDeletedException extends RuntimeException{

    public ProductNotBeDeletedException(String msg){
        super(msg);
    }
}
