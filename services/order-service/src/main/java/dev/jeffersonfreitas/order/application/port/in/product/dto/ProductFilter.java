package dev.jeffersonfreitas.order.application.port.in.product.dto;

import java.math.BigDecimal;

public record ProductFilter(
        String description,
        BigDecimal initPrice,
        BigDecimal finalPrice
) {
}
