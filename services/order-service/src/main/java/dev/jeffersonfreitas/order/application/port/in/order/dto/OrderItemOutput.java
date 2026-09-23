package dev.jeffersonfreitas.order.application.port.in.order.dto;

import dev.jeffersonfreitas.order.domain.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public record OrderItemOutput(
        String uuid,
        String productId,
        double quantity,
        BigDecimal value,
        BigDecimal total
) {
    public static List<OrderItemOutput> from(List<OrderItem> items) {
        return items.stream().map(OrderItemOutput::from).toList();
    }

    private static OrderItemOutput from(OrderItem item){
        return new OrderItemOutput(
                item.uuid().value(),
                item.productId().value(),
                item.quantity().value(),
                item.value(),
                item.total());
    }
}
