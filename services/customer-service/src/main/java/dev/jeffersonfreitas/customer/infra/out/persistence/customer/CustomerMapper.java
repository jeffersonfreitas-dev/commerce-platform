package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import java.util.HashSet;

import org.springframework.stereotype.Component;

import dev.jeffersonfreitas.customer.application.exception.BusinessException;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.valueobject.BirthDate;
import dev.jeffersonfreitas.customer.domain.valueobject.Email;
import dev.jeffersonfreitas.customer.domain.valueobject.Id;
import dev.jeffersonfreitas.customer.domain.valueobject.Name;

@Component
public final class CustomerMapper {


    public static Customer toDomain(CustomerJpaEntity entity) {
        if(entity == null){
            throw new BusinessException("Entidade não pode ser nulo ao converter para o dominio");
        }
        return new Customer(
                new Id(entity.getId()),
                new Name(entity.getName()),
                new BirthDate(entity.getBirthdate()),
                new Email(entity.getEmail()),
                entity.getCreatedAt(),
                new HashSet()
        );
    }

    public static CustomerJpaEntity toEntity(Customer customer) {
        if(customer == null){
            throw new BusinessException("Dominio não pode ser nulo ao converter para a entidade");
        }
       return new CustomerJpaEntity(
            customer.getId().value(),
            customer.getName().value(),
            customer.getEmail().value(),
            customer.getBirthdate().value(),
            customer.getCreatedAt()
        );
    }
}
