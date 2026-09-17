package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, String> {
    boolean existsByEmail(String email);
    CustomerJpaEntity findByEmail(String email);
}
