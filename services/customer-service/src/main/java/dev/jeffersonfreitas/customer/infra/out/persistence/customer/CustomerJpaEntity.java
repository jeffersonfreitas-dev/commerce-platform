package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    private Set<DeliverAddressJpaEntity> address;
}
