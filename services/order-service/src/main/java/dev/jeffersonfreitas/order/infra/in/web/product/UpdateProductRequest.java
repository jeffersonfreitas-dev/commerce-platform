package dev.jeffersonfreitas.order.infra.in.web.product;

import java.math.BigDecimal;
import java.util.List;

public record UpdateProductRequest(
        String description,
        BigDecimal price,
        BigDecimal originalPrice,
        String urlImage,
        List<String> galery,
        String category,
        List<String> tags
) {
}
