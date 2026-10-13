package dev.jeffersonfreitas.order.application.port.in.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record CreateProductInput(
        String description,
        String name,
        BigDecimal price,
        BigDecimal originalPrice,
        String urlImage,
        List<String> galery,
        String category,
        BigDecimal stock,
        List<String> tags
) {
}
