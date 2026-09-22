package dev.jeffersonfreitas.order.application.service.order;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.order.application.port.in.order.CreateOrderUseCase;
import dev.jeffersonfreitas.order.application.port.in.order.dto.CreateOrderInput;
import dev.jeffersonfreitas.order.application.port.in.order.dto.CreateOrderItemInput;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderOutput;
import dev.jeffersonfreitas.order.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.order.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.order.domain.model.Order;
import dev.jeffersonfreitas.order.domain.model.OrderItem;

import java.util.List;

public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepository repository;
    private final ProductRepository productRepository;

    public CreateOrderService(OrderRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderOutput execute(CreateOrderInput input) {
        if(input == null){
            throw new BusinessException("O input de pedido não pode ser nulo");
        }

        //TODO: validar o customerId via customer-service

        List<OrderItem> items = addAndValidateItems(input.itemInputs());
        Order order = new Order(input.customerId(), items);
        order = repository.save(order);
        return OrderOutput.from(order);
    }

    private List<OrderItem> addAndValidateItems(List<CreateOrderItemInput> inputs) {
        if(inputs == null || inputs.isEmpty()){
            throw new BusinessException("Não foi possível salvar o pedido. Itens nulos ou vazios");
        }

        inputs.forEach(item -> productRepository.get(item.productId())
                .orElseThrow(() -> new ProductNotFoundException("Não foi encontrado o produto com o código " + item.productId())));

        return inputs.stream().map(item -> new OrderItem(item.productId(), item.quantity(), item.value())).toList();

    }
}
