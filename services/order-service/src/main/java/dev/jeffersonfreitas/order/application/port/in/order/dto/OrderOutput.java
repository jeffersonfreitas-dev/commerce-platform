package dev.jeffersonfreitas.order.application.port.in.order.dto;

import dev.jeffersonfreitas.order.domain.model.Order;

public record OrderOutput(
        String uuid
) {
    public static OrderOutput from(Order order) {
        return new OrderOutput(order.uuid().value());
    }
}
