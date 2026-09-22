package dev.jeffersonfreitas.order.domain.valueobject;

import dev.jeffersonfreitas.order.domain.exception.InvalidValueObjectException;

public final class Description {

    private final String description;

    public Description(String description) {
        validate(description);
        this.description = description;
    }

    public String value(){
        return this.description;
    }

    private void validate(String description) {
        if(description == null || description.isBlank()){
            throw new InvalidValueObjectException("A descrição informado não pode ser nulo ou vazio");
        }

        if (description.length() < 3 || description.length() > 300){
            throw new InvalidValueObjectException("A descrição deve conter no mínimo 3 e máximo 300 caracteres");
        }
    }
}
