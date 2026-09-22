package dev.jeffersonfreitas.order.application.port.out.order;

import java.util.Optional;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderFilter;
import dev.jeffersonfreitas.order.domain.model.Order;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> get(String id);
    void delete(String id);
    PageGeneric<Order> fildAll(OrderFilter filter, PageableRequest pageable);
}
