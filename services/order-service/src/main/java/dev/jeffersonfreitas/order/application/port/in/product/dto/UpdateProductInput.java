package dev.jeffersonfreitas.order.application.port.in.product.dto;

import java.math.BigDecimal;

public record UpdateProductInput(
        String description,
        BigDecimal price
) {
}
