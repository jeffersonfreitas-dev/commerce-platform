package dev.jeffersonfreitas.order.application.port.out.customerservice;

import java.time.LocalDate;

public record CustomerOutput(
        String name,
        String email,
        LocalDate birthdate
) {
}
