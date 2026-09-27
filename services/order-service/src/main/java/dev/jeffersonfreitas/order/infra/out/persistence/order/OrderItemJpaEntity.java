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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "product_id", nullable = false, length = 60)
    private String productId;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderJpaEntity order;

    @Transient
    private BigDecimal total;

    public BigDecimal getTotal(){
        return quantity.multiply(total);
    }

    public OrderItemJpaEntity(String id, OrderJpaEntity order, String productId, BigDecimal quantity, BigDecimal value, BigDecimal total){
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.value = value;
        this.total = total;
        this.order = order;
    }
}
