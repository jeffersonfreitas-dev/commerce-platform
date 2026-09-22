package dev.jeffersonfreitas.order.application.port.in.product;

import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;

public interface GetProductUseCase {
    ProductOutput execute(String id);
}
