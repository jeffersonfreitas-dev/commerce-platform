package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class EntityCustomerJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<EntityDeliverAddressJpa> address;

    public EntityCustomerJpa(String id, String name, String email, LocalDate birthdate, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.createdAt = createdAt;
    }
}
