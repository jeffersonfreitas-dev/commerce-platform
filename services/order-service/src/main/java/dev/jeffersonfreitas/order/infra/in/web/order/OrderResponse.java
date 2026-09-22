package dev.jeffersonfreitas.order.infra.in.web.order;

import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderOutput;

public record OrderResponse(String id) {

    public static OrderResponse from(OrderOutput output) {
        return new OrderResponse(output.uuid());
    }

}
