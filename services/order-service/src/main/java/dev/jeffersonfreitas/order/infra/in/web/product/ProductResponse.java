package dev.jeffersonfreitas.order.infra.in.web.product;

import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ProductResponse(
        String uuid,
        String name,
        String description,
        BigDecimal price,
        BigDecimal originalPrice,
        String urlImage,
        List<String> galery,
        String category,
        BigDecimal stock,
        List<String> tags,
        double rating,
        int reviews,
        Instant createdAt,
        Instant updatedAt
) {
    public static ProductResponse from(ProductOutput output) {
        return new ProductResponse(
                output.uuid(),
                output.name(),
                output.description(),
                output.price(),
                output.originalPrice(),
                output.urlImage(),
                output.galery(),
                output.category(),
                output.stock(),
                output.tags(),
                output.rating(),
                output.reviews(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}
