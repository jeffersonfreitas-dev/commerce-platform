package dev.jeffersonfreitas.order.infra.out.persistence.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItemJpaEntity {

    @Id
    private String id;

    @Column(name = "productId", nullable = false, length = 60)
    private String productId;

    @Column(nullable = false)
    private double quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderJpaEntity order;

    @Transient
    private BigDecimal total;

    public BigDecimal getTotal(){
        return BigDecimal.valueOf(quantity).multiply(total);
    }

    public OrderItemJpaEntity(String id, String orderId, String productId, double quantity, BigDecimal value, BigDecimal total){
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.value = value;
        this.total = total;
    }
}
