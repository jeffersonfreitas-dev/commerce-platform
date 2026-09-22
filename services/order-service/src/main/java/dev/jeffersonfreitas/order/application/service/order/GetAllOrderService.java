package dev.jeffersonfreitas.order.application.service.order;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.port.in.order.GetAllOrderUseCase;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderFilter;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderOutput;
import dev.jeffersonfreitas.order.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.order.domain.model.Order;

public class GetAllOrderService implements GetAllOrderUseCase{

    private final OrderRepository orderRepository;

    public GetAllOrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public PageGeneric<OrderOutput> execute(OrderFilter filter, PageableRequest pageable) {
        PageGeneric<Order> orderGeneric = orderRepository.fildAll(filter, pageable);
        return orderGeneric.map(OrderOutput::from);
    }
}
