package dev.jeffersonfreitas.order.infra.out.persistence.order;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderFilter;
import dev.jeffersonfreitas.order.application.port.out.order.OrderRepository;
import dev.jeffersonfreitas.order.domain.model.Order;
import dev.jeffersonfreitas.order.infra.out.PageRequestMapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryInfra implements OrderRepository {

    private final OrderJpaRepository repository;

    public OrderRepositoryInfra(OrderJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = OrderMapper.toEntity(order);
        entity = repository.save(entity);
        return OrderMapper.toDomain(entity);
    }

    @Override
    public Optional<Order> get(String id) {
        return repository.findById(id).map(OrderMapper::toDomain);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public PageGeneric<Order> fildAll(OrderFilter filter, PageableRequest pageableRequest) {
        Pageable pageable = PageRequestMapper.toSpring(pageableRequest);
        Specification<OrderJpaEntity> entitySpecification = OrderSpecifications.from(filter);
        Page<OrderJpaEntity> orders = repository.findAll(entitySpecification, pageable);
        List<Order> ordersList = orders.stream().map(OrderMapper::toDomain).toList();
        return new PageGeneric<>(
            ordersList,
            orders.getNumber(),
            orders.getSize(),
            orders.getNumberOfElements(),
            orders.getTotalPages()
        );
    }
}
