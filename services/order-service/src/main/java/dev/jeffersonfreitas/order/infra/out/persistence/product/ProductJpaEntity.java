package dev.jeffersonfreitas.order.infra.out.persistence.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 300)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "original_price")
    private BigDecimal originalPrice;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private List<String> galery;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private BigDecimal stock;

    @Column(nullable = false)
    private BigDecimal rating;

    @Column(nullable = false)
    private Integer reviews;

    @Column(nullable = false)
    private List<String> tags;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
