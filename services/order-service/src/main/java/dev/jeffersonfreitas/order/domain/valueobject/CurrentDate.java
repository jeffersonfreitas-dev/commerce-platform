package dev.jeffersonfreitas.order.domain.valueobject;

import dev.jeffersonfreitas.order.domain.exception.InvalidValueObjectException;

import java.time.LocalDate;

public final class CurrentDate {

    private final LocalDate date;

    public CurrentDate(){
        this.date = LocalDate.now();
    }

    public CurrentDate(LocalDate date){
        validate(date);
        this.date = date;
    }

    public LocalDate value(){
        return this.date;
    }

    private void validate(LocalDate date) {
        if(date == null){
            throw new InvalidValueObjectException("A data não pode ser nula");
        }

        if(date.isAfter(LocalDate.now())){
            throw new InvalidValueObjectException("A data não pode ser no futuro");
        }
    }


}
