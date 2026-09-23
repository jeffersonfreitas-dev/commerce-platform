package dev.jeffersonfreitas.order.infra.out.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, String>, JpaSpecificationExecutor<OrderJpaEntity> {

    @Query("SELECT o FROM OrderJpaEntity o join fetch o.items i WHERE o.id = :id")
    Optional<OrderJpaEntity> findByIdAndItems(@Param("id") String id);
}
