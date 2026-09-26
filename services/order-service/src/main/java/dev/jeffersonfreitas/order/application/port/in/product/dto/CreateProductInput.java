package dev.jeffersonfreitas.order.application.port.in.product.dto;

import java.math.BigDecimal;

public record CreateProductInput(String description, String name, BigDecimal price) {
}
