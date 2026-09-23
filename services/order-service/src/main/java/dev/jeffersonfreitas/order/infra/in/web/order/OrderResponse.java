package dev.jeffersonfreitas.order.infra.in.web.order;

import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderOutput;
import dev.jeffersonfreitas.order.domain.model.OrderStatus;
import dev.jeffersonfreitas.order.infra.in.web.order.items.OrderItemResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String uuid,
        String customerId,
        Instant date,
        BigDecimal total,
        OrderStatus status,
        List<OrderItemResponse> items
) {

    public static OrderResponse from(OrderOutput output) {
        List<OrderItemResponse> items = OrderItemResponse.from(output.items());
        return new OrderResponse(
                output.uuid(),
                output.customerId(),
                output.date(),
                output.total(),
                output.status(),
                items
        );
    }

}
