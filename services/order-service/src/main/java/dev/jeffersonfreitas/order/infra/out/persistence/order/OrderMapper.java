package dev.jeffersonfreitas.order.infra.out.persistence.order;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.domain.model.Order;
import dev.jeffersonfreitas.order.domain.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class OrderMapper {

    public static Order toDomain(OrderJpaEntity entity){
        if(entity == null){
            throw new BusinessException("Entidade não pode ser nulo ao converter para o dominio");
        }

        List<OrderItem> items = toDomainItem(entity.getItems());
        return new Order(entity.getId(), entity.getCustomerId(), entity.getDate(), entity.isActive(), entity.getTotal(), entity.getStatus(), items);
    }

    public static OrderJpaEntity toEntity(Order order){
        if(order == null){
            throw new BusinessException("Dominio não pode ser nulo ao converter para a entidade");
        }
        String orderId = Objects.nonNull(order.uuid()) ? order.uuid().value() : null;
        OrderJpaEntity entity =  new OrderJpaEntity(
                orderId,
                order.customerId().value(),
                order.date(),
                order.active(),
                order.total(),
                order.status().name());
        List<OrderItemJpaEntity> items = toEntityItem(entity, order.items());
        entity.setItems(items);
        return entity;
    }

    private static List<OrderItemJpaEntity> toEntityItem(OrderJpaEntity order, List<OrderItem> items) {
        return items.stream().map(item ->
                new OrderItemJpaEntity(
                        Objects.nonNull(item.uuid()) ? item.uuid().value() : null,
                        order,
                        item.productId().value(),
                        item.quantity().value(),
                        item.value(),
                        item.total())).toList();
    }

    private static List<OrderItem> toDomainItem(List<OrderItemJpaEntity> items) {
        return items.stream().map(
                item -> new OrderItem(
                        item.getId(),
                        item.getProductId(),
                        item.getQuantity(),
                        item.getValue(),
                        null)).toList();
    }
}
