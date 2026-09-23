package dev.jeffersonfreitas.order.infra.in.web.order.items;

import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderItemOutput;

import java.math.BigDecimal;
import java.util.List;

public record OrderItemResponse(
        String uuid,
        String productId,
        double quantity,
        BigDecimal value,
        BigDecimal total
) {
    public static List<OrderItemResponse> from(List<OrderItemOutput> items) {
        return items.stream().map(OrderItemResponse::from).toList();
    }

    private static OrderItemResponse from(OrderItemOutput item){
        return new OrderItemResponse(
                item.uuid(),
                item.productId(),
                item.quantity(),
                item.value(),
                item.total());
    }
}
