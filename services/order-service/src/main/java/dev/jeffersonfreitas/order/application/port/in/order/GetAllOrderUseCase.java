package dev.jeffersonfreitas.order.application.port.in.order;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderFilter;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderOutput;

public interface GetAllOrderUseCase {
    PageGeneric<OrderOutput> execute(OrderFilter filter, PageableRequest pageable);
}
