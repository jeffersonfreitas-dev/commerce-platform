package dev.jeffersonfreitas.order.domain.model;

import dev.jeffersonfreitas.order.domain.valueobject.Identity;
import dev.jeffersonfreitas.order.domain.valueobject.Quantity;

import java.math.BigDecimal;

public class OrderItem {

    private final Identity uuid;
    private final Identity productId;
    private final Quantity quantity;
    private final BigDecimal value;
    private final BigDecimal total;

    public OrderItem(String productId, double quantity, BigDecimal value){
        this.uuid = new Identity();
        this.productId = new Identity(productId);
        this.quantity = new Quantity(quantity);
        this.value = value;
        this.total = BigDecimal.valueOf(quantity).multiply(value);
    }

    public OrderItem(String uuid, String productId, double quantity, BigDecimal value, BigDecimal total){
        this.uuid = new Identity(uuid);
        this.productId = new Identity(productId);
        this.quantity = new Quantity(quantity);
        this.value = value;
        this.total = total;
    }

    public BigDecimal total() {
        return total;
    }

    public Identity uuid(){
        return this.uuid;
    }

    public Identity productId(){
        return this.productId;
    }

    public Quantity quantity() {
        return this.quantity;
    }

    public BigDecimal value(){
        return this.value;
    }
}
