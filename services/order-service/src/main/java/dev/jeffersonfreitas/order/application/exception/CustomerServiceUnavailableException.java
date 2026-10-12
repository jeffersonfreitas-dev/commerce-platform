package dev.jeffersonfreitas.order.application.exception;

public class CustomerServiceUnavailableException extends RuntimeException{
    public CustomerServiceUnavailableException(String originalMessage) {
        super(originalMessage);
    }

    public CustomerServiceUnavailableException() {
        super("Customer Service não disponível");
    }
}
