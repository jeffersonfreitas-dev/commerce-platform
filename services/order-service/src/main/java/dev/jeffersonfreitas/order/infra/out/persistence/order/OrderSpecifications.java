package dev.jeffersonfreitas.order.infra.out.persistence.order;

import org.springframework.data.jpa.domain.Specification;

import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderFilter;

public class OrderSpecifications {

    public static Specification<OrderJpaEntity> from (OrderFilter filter){
        Specification<OrderJpaEntity> specification = null;

        if(filter.id() != null){
            specification = and(specification, idIs(filter.id()));
        }

        return specification;
    }

    private static Specification<OrderJpaEntity> and(Specification<OrderJpaEntity> current,
                                                        Specification<OrderJpaEntity> next) {
        return current == null ? next : current.and(next);
    }
    
    private static Specification<OrderJpaEntity> idIs(String id){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

}
