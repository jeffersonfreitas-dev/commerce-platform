package dev.jeffersonfreitas.order.application.service.product;

import dev.jeffersonfreitas.order.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.order.application.port.in.product.UpdateProductUseCase;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.order.application.port.in.product.dto.UpdateProductInput;
import dev.jeffersonfreitas.order.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.order.domain.model.Product;

public class UpdateProductService implements UpdateProductUseCase {

    private final ProductRepository repository;

    public UpdateProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductOutput execute(String id, UpdateProductInput input) {
        Product product = repository.get(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado para realizar a alteração"));

        product.setDescription(input.description());
        product.setPrice(input.price());
        Product updatedProduct = new Product(product);
        repository.save(updatedProduct);
        return ProductOutput.from(updatedProduct);
    }
}
