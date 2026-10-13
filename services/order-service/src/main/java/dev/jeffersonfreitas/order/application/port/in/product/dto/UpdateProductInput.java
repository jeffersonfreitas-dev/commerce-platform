package dev.jeffersonfreitas.order.application.port.in.product.dto;

import java.math.BigDecimal;
import java.util.List;

public record UpdateProductInput(
        String description,
        BigDecimal price,
        BigDecimal originalPrice,
        String urlImage,
        List<String> galery,
        String category,
        List<String> tags
) {
}
