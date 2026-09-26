package dev.jeffersonfreitas.order.infra.out.persistence.order;

import org.springframework.data.jpa.domain.Specification;

import dev.jeffersonfreitas.order.application.port.in.order.dto.OrderFilter;

import java.time.Instant;

public class OrderSpecifications {

    public static Specification<OrderJpaEntity> from (OrderFilter filter){
        Specification<OrderJpaEntity> specification = null;

        if(filter.uuid() != null && !filter.uuid().isBlank()){
            specification = and(specification, idIs(filter.uuid()));
        }
        if(filter.customerId() != null && !filter.customerId().isBlank()){
            specification = and(specification, customerIdIs(filter.customerId()));
        }
        if(filter.dateIni() != null){
            specification = and(specification, dateIniIs(filter.dateIni()));
        }
        if(filter.dateFim() != null){
            specification = and(specification, dateFimIs(filter.dateFim()));
        }
        return specification;
    }

    private static Specification<OrderJpaEntity> dateFimIs(Instant instant) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("date"), instant);
    }

    private static Specification<OrderJpaEntity> dateIniIs(Instant instant) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("date"), instant);
    }

    private static Specification<OrderJpaEntity> customerIdIs(String customerId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("customerId"), customerId);
    }

    private static Specification<OrderJpaEntity> idIs(String id){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id);
    }

    private static Specification<OrderJpaEntity> and(Specification<OrderJpaEntity> current,Specification<OrderJpaEntity> next) {
        return current == null ? next : current.and(next);
    }
}
