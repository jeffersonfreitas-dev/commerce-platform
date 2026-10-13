package dev.jeffersonfreitas.order.application.port.in.product.dto;

import dev.jeffersonfreitas.order.domain.model.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ProductOutput(
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
    public static ProductOutput from(Product product) {
        return new ProductOutput(
            product.uuid(),
            product.name(),
            product.description(),
            product.price(),
            product.originalPrice(),
            product.urlImage(),
            product.imageGalery(),
            product.category(),
            product.stock(),
            product.tags(),
            product.rating(),
            product.reviews(),
            product.createdAt(),
            product.updatedAt()
        );
    }
}
