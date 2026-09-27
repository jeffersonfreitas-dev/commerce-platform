package dev.jeffersonfreitas.order.domain.valueobject;

import dev.jeffersonfreitas.order.domain.exception.InvalidValueObjectException;

import java.math.BigDecimal;

public final class Quantity {
    private final BigDecimal quantity;

    public Quantity(BigDecimal quantity){
        validate(quantity);
        this.quantity = quantity;
    }

    public BigDecimal value(){
        return this.quantity;
    }

    private void validate(BigDecimal quantity){
        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidValueObjectException("O valor informado não pode ser nulo ou menor/igual a zero");
        }
    }

}
