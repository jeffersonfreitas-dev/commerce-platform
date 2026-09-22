package dev.jeffersonfreitas.order.application.port.in.product.dto;

import dev.jeffersonfreitas.order.domain.model.Product;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductOutput(
        String uuid,
        String description,
        BigDecimal price,
        Instant createdAt,
        Instant updatedAt
) {
    public static ProductOutput from(Product product) {
        return new ProductOutput(
                product.uuid(),
                product.description(),
                product.price(),
                product.createdAt(),
                product.updatedAt()
        );
    }
}
