package dev.jeffersonfreitas.order.application.port.out.product;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {

    boolean existsByName(String description);
    Product save(Product product);
    void delete(String id);
    PageGeneric<Product> getAll(String filter, PageableRequest pageable);
    Optional<Product> get(String id);
}
