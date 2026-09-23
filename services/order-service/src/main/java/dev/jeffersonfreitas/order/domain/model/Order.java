package dev.jeffersonfreitas.order.domain.model;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.domain.valueobject.Identity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public final class Order {

    private Identity uuid;
    private final Identity customerId;
    private final Instant date;
    private final boolean active;
    private final BigDecimal total;
    private OrderStatus status;
    private final List<OrderItem> items;

    public Order(String customerId, List<OrderItem> items){
        this.customerId = new Identity(customerId);
        this.date = Instant.now();
        this.active = true;
        this.items = addItems(items);
        this.status = OrderStatus.CREATED;
        this.total = calculateTotal(items);
    }

    public Order(String uuid, String customerId, Instant date, boolean active, BigDecimal total, String status, List<OrderItem> items){
        this.uuid = new Identity(uuid);
        this.customerId = new Identity(customerId);
        this.date = date;
        this.active = active;
        this.total = total;
        this.status = OrderStatus.from(status);
        this.items = items;
    }

    public void cancelled(){
        this.status = OrderStatus.CANCELLED;
    }

    public void paymentPending(){
        this.status = OrderStatus.PAYMENT_PENDING;
    }

    public void paymentFailed(){
        this.status = OrderStatus.PAYMENT_FAILED;
    }

    public void payed(){
        this.status = OrderStatus.PAID;
    }

    public boolean isDeletable(){
        return this.status.equals(OrderStatus.CREATED);
    }

    public boolean isCancellable(){
        return this.isDeletable() && this.status.equals(OrderStatus.PAYMENT_FAILED);
    }

    public Identity uuid(){
        return this.uuid;
    }

    public Identity customerId(){
        return this.customerId;
    }

    public Instant date(){
        return this.date;
    }

    public boolean active(){
        return this.active;
    }

    public BigDecimal total(){
        return this.total;
    }

    public OrderStatus status(){
        return this.status;
    }

    public List<OrderItem> items(){
        return this.items;
    }

    private List<OrderItem> addItems(List<OrderItem> items) {
        if(items == null || items.isEmpty()){
            throw new BusinessException("O pedido deve conter, no mínimo, um item");
        }
        return items;
    }


    private BigDecimal calculateTotal(List<OrderItem> items) {
        if (items == null || items.isEmpty()){
            throw new BusinessException("A lista de itens do pedido não pode ser nula ou vazia");
        }

        BigDecimal total = items.stream().map(OrderItem::total).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (total.compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException("O total do pedido não pode ser menor ou igual a zero");
        }
        return total;
    }
}
