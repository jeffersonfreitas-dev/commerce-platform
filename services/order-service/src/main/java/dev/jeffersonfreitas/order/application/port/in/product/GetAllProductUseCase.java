package dev.jeffersonfreitas.order.application.port.in.product;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;

public interface GetAllProductUseCase {
    PageGeneric<ProductOutput> execute(String filter, PageableRequest pageable);
}
