package dev.jeffersonfreitas.order.application.port.in.order.dto;

import java.math.BigDecimal;

public record CreateOrderItemInput(String productId, double quantity, BigDecimal value) {
}
