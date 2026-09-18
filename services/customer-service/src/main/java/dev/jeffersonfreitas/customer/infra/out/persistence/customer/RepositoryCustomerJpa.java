package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepositoryCustomerJpa extends JpaRepository<EntityCustomerJpa, String> {
    boolean existsByEmail(String email);

    @Query ("SELECT c FROM EntityCustomerJpa c join fetch c.address a where c.email = :email" )
    Optional<EntityCustomerJpa> findByEmail(@Param("email") String email);
}
