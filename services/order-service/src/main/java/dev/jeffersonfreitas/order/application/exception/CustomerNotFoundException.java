package dev.jeffersonfreitas.order.application.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String originalMessage) {
        super(originalMessage);
    }

    public CustomerNotFoundException() {
        super("Cliente não encontrado");
    }
}
