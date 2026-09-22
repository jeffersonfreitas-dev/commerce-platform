package dev.jeffersonfreitas.order.application.port.in.product;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;

public interface GetAllProductUseCase {
    //TODO: Usar o Solr, Postgres ou Elastic
    PageGeneric<ProductOutput> execute(ProductFilter filter, PageableRequest pageable);
}
