package dev.jeffersonfreitas.order.application.port.in.order.dto;

import dev.jeffersonfreitas.order.domain.model.Order;
import dev.jeffersonfreitas.order.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderOutput(
        String uuid,
        String customerId,
        Instant date,
        BigDecimal total,
        OrderStatus status,
        List<OrderItemOutput> items
) {
    public static OrderOutput from(Order order) {
        List<OrderItemOutput> items = OrderItemOutput.from(order.items());
        return new OrderOutput(
                order.uuid().value(),
                order.customerId().value(),
                order.date(),
                order.total(),
                order.status(),
                items
        );
    }
}
