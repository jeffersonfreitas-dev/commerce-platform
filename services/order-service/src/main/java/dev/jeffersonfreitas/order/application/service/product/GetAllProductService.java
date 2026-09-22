package dev.jeffersonfreitas.order.application.service.product;

import dev.jeffersonfreitas.order.application.dto.PageGeneric;
import dev.jeffersonfreitas.order.application.dto.PageableRequest;
import dev.jeffersonfreitas.order.application.port.in.product.GetAllProductUseCase;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductFilter;
import dev.jeffersonfreitas.order.application.port.in.product.dto.ProductOutput;
import dev.jeffersonfreitas.order.application.port.out.product.ProductRepository;
import dev.jeffersonfreitas.order.domain.model.Product;

public class GetAllProductService implements GetAllProductUseCase {

    private final ProductRepository repository;

    public GetAllProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public PageGeneric<ProductOutput> execute(ProductFilter filter, PageableRequest pageable) {
        PageGeneric<Product> productPageGeneric = repository.getAll(filter, pageable);
        return productPageGeneric.map(ProductOutput::from);
    }
}
