package dev.jeffersonfreitas.order.infra.in.web.product;

import java.math.BigDecimal;

public record UpdateProductRequest(
        String description,
        BigDecimal price
) {
}
