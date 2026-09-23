package dev.jeffersonfreitas.order.infra.in.web.product;

import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        String uuid,
        String description,
        BigDecimal price,
        Instant createdAt,
        Instant updatedAt
) {
    public static ProductResponse from(ProductOutput output) {
        return new ProductResponse(
                output.uuid(),
                output.description(),
                output.price(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}
