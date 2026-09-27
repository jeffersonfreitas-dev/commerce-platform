package dev.jeffersonfreitas.order.application.port.in.order.dto;

import java.math.BigDecimal;

public record CreateOrderItemInput(String productId, BigDecimal quantity, BigDecimal value) {
}
