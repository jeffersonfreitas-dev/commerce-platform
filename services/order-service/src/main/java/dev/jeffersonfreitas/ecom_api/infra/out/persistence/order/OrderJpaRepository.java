package dev.jeffersonfreitas.ecom_api.infra.out.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, String>, JpaSpecificationExecutor<OrderJpaEntity> {
}
