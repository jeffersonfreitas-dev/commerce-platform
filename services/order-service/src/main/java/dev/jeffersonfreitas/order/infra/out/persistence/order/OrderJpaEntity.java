package dev.jeffersonfreitas.order.infra.out.persistence.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "customerId", nullable = false, length = 60)
    private String customerId;

    @Column(nullable = false)
    private Instant date;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false, length = 20)
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemJpaEntity> items;

}
