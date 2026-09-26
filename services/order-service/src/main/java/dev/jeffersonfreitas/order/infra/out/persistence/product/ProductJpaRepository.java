package dev.jeffersonfreitas.order.infra.out.persistence.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, String>, JpaSpecificationExecutor<ProductJpaEntity> {
    boolean existsByName(String name);

    @Query(
            value = """
        SELECT p.*
        FROM products p
        WHERE (
            setweight(
                to_tsvector('portuguese', coalesce(p.name, '')),
                'A'
            ) ||
            setweight(
                to_tsvector('portuguese', coalesce(p.description, '')),
                'B'
            )
        ) @@ websearch_to_tsquery('portuguese', :text)
        ORDER BY ts_rank(
            (
                setweight(
                    to_tsvector('portuguese', coalesce(p.name, '')),
                    'A'
                ) ||
                setweight(
                    to_tsvector('portuguese', coalesce(p.description, '')),
                    'B'
                )
            ),
            websearch_to_tsquery('portuguese', :text)
        ) DESC
        """, countQuery = """
        SELECT COUNT(*)
        FROM products p
        WHERE (
            setweight(
                to_tsvector('portuguese', coalesce(p.name, '')),
                'A'
            ) ||
            setweight(
                to_tsvector('portuguese', coalesce(p.description, '')),
                'B'
            )
        ) @@ websearch_to_tsquery('portuguese', :text)
        """,
            nativeQuery = true
    )
    Page<ProductJpaEntity> search(@Param("text") String text, Pageable pageable);
}
