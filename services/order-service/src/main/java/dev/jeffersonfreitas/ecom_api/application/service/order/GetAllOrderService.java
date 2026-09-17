package dev.jeffersonfreitas.ecom_api.application.service.order;

import dev.jeffersonfreitas.ecom_api.application.dto.PageGeneric;
import dev.jeffersonfreitas.ecom_api.application.dto.PageableRequest;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.GetAllOrderUseCase;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderFilter;
import dev.jeffersonfreitas.ecom_api.application.port.in.order.dto.OrderOutput;
import dev.jeffersonfreitas.ecom_api.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.ecom_api.domain.model.order.Order;

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
