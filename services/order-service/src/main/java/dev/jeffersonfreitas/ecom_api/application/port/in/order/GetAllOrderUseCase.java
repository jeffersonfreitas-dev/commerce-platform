package dev.jeffersonfreitas.ecom_api.application.port.in.order;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequest;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;

public interface GetAllOrderUseCase {
    PageGeneric<OrderOutput> execute(OrderFilter filter, PageableRequest pageable);
}
