package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<EntityCustomerJpa, String> {
    boolean existsByEmail(String email);
    Optional<EntityCustomerJpa> findByEmail(String email);
}
