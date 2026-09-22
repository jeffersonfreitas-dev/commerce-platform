package dev.jeffersonfreitas.order.infra.out.persistence.product;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.domain.model.Product;
import dev.jeffersonfreitas.order.domain.valueobject.Description;
import dev.jeffersonfreitas.order.domain.valueobject.Identity;
import org.springframework.stereotype.Component;

@Component
public final class ProductMapper {

    public static ProductJpaEntity toEntity(Product product) {
        if(product == null){
            throw new BusinessException("Dominio não pode ser nulo ao converter para a entidade");
        }
        return new ProductJpaEntity(
                product.uuid(),
                product.description(),
                product.price(),
                product.createdAt(),
                product.updatedAt()
        );
    }

    public static Product toDomain(ProductJpaEntity entity) {
        if(entity == null){
            throw new BusinessException("Entidade não pode ser nulo ao converter para o dominio");
        }
        return new Product(
                entity.getId(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
