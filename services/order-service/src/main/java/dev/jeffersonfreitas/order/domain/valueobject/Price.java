package dev.jeffersonfreitas.order.domain.valueobject;

import dev.jeffersonfreitas.order.domain.exception.InvalidValueObjectException;

import java.math.BigDecimal;

public final class Price {
    private final BigDecimal price;

    public Price(BigDecimal price){
        validate(price);
        this.price = price;
    }

    public BigDecimal value(){
        return this.price;
    }

    private void validate(BigDecimal price){
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidValueObjectException("O preço informado não pode ser nulo ou menor/igual a zero");
        }
    }

}
