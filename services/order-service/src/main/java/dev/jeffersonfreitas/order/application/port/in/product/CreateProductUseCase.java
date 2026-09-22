package dev.jeffersonfreitas.order.application.port.in.product;

import dev.jeffersonfreitas.order.application.port.in.product.dto.CreateProductInput;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;

public interface CreateProductUseCase {
    ProductOutput execute(CreateProductInput input);
}
