package dev.jeffersonfreitas.customer.domain.valueobject;

import dev.jeffersonfreitas.customer.domain.exception.InvalidValueObjectException;

public class ZipCode {

    private final String code;


    public ZipCode(String code){
        validate(code);
        this.code = code;
    }

    public String value(){
        return this.code;
    }


    private void validate(String code) {
        if(code == null || code.isBlank()){
            throw new InvalidValueObjectException("O CEP não pode ser nulo ou vazio");
        }

        if (code.length() < 6 || code.length() > 6){
            throw new InvalidValueObjectException("O CEP deve conter exatamente seis digitos");
        }
    }

}
