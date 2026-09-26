package dev.jeffersonfreitas.order.domain.valueobject;

import dev.jeffersonfreitas.order.domain.exception.InvalidValueObjectException;

public final class Name {

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public String value(){
        return this.name;
    }

    private void validate(String name) {
        if(name == null || name.isBlank()){
            throw new InvalidValueObjectException("O nome informado não pode ser nulo ou vazio");
        }

        if (name.length() < 3 || name.length() > 100){
            throw new InvalidValueObjectException("A descrição deve conter no mínimo 3 e máximo 100 caracteres");
        }
    }
}
