package dev.jeffersonfreitas.order.application.service.order;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.application.exception.OrderNotFoundException;
import dev.jeffersonfreitas.order.application.port.in.order.CancelOrderUseCase;
import dev.jeffersonfreitas.order.application.port.in.order.DeleteOrderUseCase;
import dev.jeffersonfreitas.order.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.order.domain.model.Order;

public class CancelOrderService implements CancelOrderUseCase {

    private final OrderRepository repository;

    public CancelOrderService(OrderRepository repository){
        this.repository = repository;
    }

    @Override
    public void execute(String id) {
        if (id == null || id.isBlank()){
            throw new BusinessException("O código não pode ser nulo ou vazio ao cancelar o pedido");
        }
        Order order = repository.get(id)
                .orElseThrow(() -> new OrderNotFoundException("O pedido não foi encontrado para realizar o cancelamento"));

        if(!order.isCancellable()){
            throw new BusinessException("O pedido não pode ser cancelado, pois está com o status que não permite");
        }
        order.cancelled();
        repository.save(order);
    }

}
