package dev.jeffersonfreitas.order.domain.valueobject;

import dev.jeffersonfreitas.order.domain.exception.InvalidValueObjectException;

import java.util.UUID;

public final class Identity {
    private final String value;

    public Identity(){
        this.value = UUID.randomUUID().toString();
    }

    public Identity(String uuid){
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
}
