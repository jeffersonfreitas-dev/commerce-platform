package dev.jeffersonfreitas.order.application.service.product;

import dev.jeffersonfreitas.order.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.order.application.port.in.product.GetProductUseCase;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.order.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.order.domain.model.Product;

public class GetProductService implements GetProductUseCase {

    private final ProductRepository repository;

    public GetProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductOutput execute(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("O código informado do produto não pode ser nulo ou vazio");
        }
        Product product = repository.get(id)
                .orElseThrow(() -> new ProductNotFoundException("Não existe produto com o código informado"));
        return ProductOutput.from(product);
    }
}
