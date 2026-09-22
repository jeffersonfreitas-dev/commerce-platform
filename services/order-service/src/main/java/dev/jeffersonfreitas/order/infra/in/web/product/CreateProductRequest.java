package dev.jeffersonfreitas.order.infra.in.web.product;

import java.math.BigDecimal;

public record CreateProductRequest(
        String description,
        BigDecimal price
) {
}
