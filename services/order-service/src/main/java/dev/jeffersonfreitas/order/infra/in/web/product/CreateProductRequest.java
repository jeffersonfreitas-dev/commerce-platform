package dev.jeffersonfreitas.order.infra.in.web.product;

import java.math.BigDecimal;
import java.util.List;

public record CreateProductRequest(
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
