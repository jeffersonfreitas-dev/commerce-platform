package dev.jeffersonfreitas.order.domain.valueobject;

import dev.jeffersonfreitas.order.domain.exception.InvalidValueObjectException;

public final class Quantity {
    private final double quantity;

    public Quantity(double quantity){
        validate(quantity);
        this.quantity = quantity;
    }

    public double value(){
        return this.quantity;
    }

    private void validate(double quantity){
        if (quantity <= 0.0 ){
            throw new InvalidValueObjectException("O valor informado não pode ser nulo ou menor/igual a zero");
        }
    }

}
