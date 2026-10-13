package dev.jeffersonfreitas.order.infra.out.persistence.product;

import dev.jeffersonfreitas.order.application.exception.BusinessException;
import dev.jeffersonfreitas.order.domain.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public final class ProductMapper {

    public static ProductJpaEntity toEntity(Product product) {
        if(product == null){
            throw new BusinessException("Dominio não pode ser nulo ao converter para a entidade");
        }
        return new ProductJpaEntity(
                product.uuid(),
                product.name(),
                product.description(),
                product.price(),
                product.originalPrice(),
                product.urlImage(),
                product.imageGalery(),
                product.category(),
                product.stock(),
                BigDecimal.valueOf(product.rating()),
                product.reviews(),
                product.tags(),
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
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getOriginalPrice(),
                entity.getImage(),
                entity.getGalery(),
                entity.getCategory(),
                entity.getStock(),
                entity.getTags(),
                entity.getRating().doubleValue(),
                entity.getReviews(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
