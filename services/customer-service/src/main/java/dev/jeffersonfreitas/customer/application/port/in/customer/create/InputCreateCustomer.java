package dev.jeffersonfreitas.customer.application.port.in.customer.create;

import java.time.LocalDate;

public record InputCreateCustomer(
        String name,
        String email,
        LocalDate birthdate,
        InputCreateDeliverAddress address
) {
}
