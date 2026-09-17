package dev.jeffersonfreitas.customer.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

import dev.jeffersonfreitas.customer.domain.exception.InvalidValueObjectException;

public final class Id {
    private final String value;

    public Id(){
        this.value = UUID.randomUUID().toString();
    }

    public Id(String uuid){
        try{
            UUID.fromString(uuid);
            this.value = uuid;
        }catch (IllegalArgumentException err){
            throw new InvalidValueObjectException("O código informado é inválido");
        }
    }

    public String value(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id other)) return false;
        return Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
