package dev.jeffersonfreitas.customer.domain.valueobject;

import java.time.LocalDate;

import dev.jeffersonfreitas.customer.domain.exception.InvalidValueObjectException;

public class BirthDate {

    private final LocalDate date;

    public BirthDate(LocalDate date){
        validate(date);
        this.date= date;
    }

    public LocalDate value(){
        return this.date;
    }

    private void validate(LocalDate date) {
        if(date == null){
            throw new InvalidValueObjectException("A data de nascimento não pode ser nula");
        }

        if(date.isAfter(LocalDate.now())){
            throw new InvalidValueObjectException("A data de nascimento não pode ser no futuro");
        }
    }

}
