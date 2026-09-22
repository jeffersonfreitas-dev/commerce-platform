package dev.jeffersonfreitas.order.application.service.product;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.application.exception.ProductNotBeDeletedException;
import dev.jeffersonfreitas.order.application.exception.ProductNotFoundException;
import dev.jeffersonfreitas.order.application.port.in.product.DeleteProductUseCase;
import dev.jeffersonfreitas.order.application.port.out.product.ProductRepository;

public class DeleteProductService implements DeleteProductUseCase {

    private final ProductRepository repository;

    public DeleteProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String id) {
        if (id == null || id.isBlank()){
            throw new BusinessException("O código não pode ser nulo ou vazio ao deletar");
        }
        repository.get(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado para exclusão"));

        try{
            repository.delete(id);
        }catch (Exception e){
            throw new ProductNotBeDeletedException("Este produto não pode ser excluído");
        }

    }
}
