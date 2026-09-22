package dev.jeffersonfreitas.order.application.service.order;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.application.exception.OrderNotFoundException;
import dev.jeffersonfreitas.order.application.port.in.order.GetOrderUseCase;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderOutput;
import dev.jeffersonfreitas.order.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.order.domain.model.Order;

public class GetOrderService implements GetOrderUseCase{

    private final OrderRepository repository;

    public GetOrderService(OrderRepository repository){
        this.repository = repository;
    }

    @Override
    public OrderOutput execute(String id) {
        if(id == null || id.isBlank()){
            throw new BusinessException("O código do pedido não pode ser nulo ou vazio");
        }

        Order order = repository.get(id)
            .orElseThrow(() -> new OrderNotFoundException("Não foi encontrado o pedido com o código informado"));
        return OrderOutput.from(order);
    }

}
