package dev.jeffersonfreitas.order.application.port.out.product;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.order.domain.model.Product;

import java.util.Optional;

public interface ProductRepository {

    boolean existsByDescription(String description);
    Product save(Product product);
    void delete(String id);
    PageGeneric<Product> getAll(ProductFilter filter, PageableRequest pageable);
    Optional<Product> get(String id);
}
