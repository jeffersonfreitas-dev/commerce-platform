package dev.jeffersonfreitas.order.application.port.in.order;

import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderOutput;

public interface GetOrderUseCase {

    OrderOutput execute(String id);

}
