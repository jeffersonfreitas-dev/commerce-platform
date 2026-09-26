package dev.jeffersonfreitas.order.application.port.in.order.dto;

import java.time.Instant;

public record OrderFilter(
        String uuid,
        String customerId,
        Instant dateIni,
        Instant dateFim
) {

}
