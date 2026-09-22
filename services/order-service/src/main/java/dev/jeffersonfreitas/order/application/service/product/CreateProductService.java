package dev.jeffersonfreitas.order.application.service.product;

import dev.jeffersonfreitas.order.application.port.in.product.dto.CreateProductInput;
import dev.jeffersonfreitas.order.application.port.in.product.CreateProductUseCase;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.order.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.order.domain.exception.ProductAlreadyExistsException;
import dev.jeffersonfreitas.order.domain.model.Product;

public class CreateProductService implements CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductOutput execute(CreateProductInput input) {
        if(productRepository.existsByDescription(input.description())){
            throw new ProductAlreadyExistsException("Já existe um produto cadastrado com esta descrição");
        }
        Product product = new Product(input.description(), input.price());
        Product productSaved = productRepository.save(product);
        return ProductOutput.from(productSaved);
    }
}
