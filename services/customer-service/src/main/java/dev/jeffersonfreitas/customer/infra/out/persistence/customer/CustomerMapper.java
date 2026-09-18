package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import dev.jeffersonfreitas.customer.application.exception.BusinessException;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;
import dev.jeffersonfreitas.customer.domain.valueobject.BirthDate;
import dev.jeffersonfreitas.customer.domain.valueobject.Email;
import dev.jeffersonfreitas.customer.domain.valueobject.Id;
import dev.jeffersonfreitas.customer.domain.valueobject.Name;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public final class CustomerMapper {


    public static Customer toDomain(EntityCustomerJpa entity) {
        if(entity == null){
            throw new BusinessException("Entidade não pode ser nulo ao converter para o dominio");
        }
        Set<DeliverAddress> addressList = entity.getAddress().stream().map(CustomerMapper::toDomain).collect(Collectors.toSet());
        return new Customer(
                new Id(entity.getId()),
                new Name(entity.getName()),
                new BirthDate(entity.getBirthdate()),
                new Email(entity.getEmail()),
                entity.getCreatedAt(),
                addressList
        );
    }

    public static EntityCustomerJpa toEntity(Customer customer) {
        if(customer == null){
            throw new BusinessException("Dominio não pode ser nulo ao converter para a entidade");
        }

        List<EntityDeliverAddressJpa> addressJpas = new ArrayList<>();

        EntityCustomerJpa customerEntity =  new EntityCustomerJpa(
            customer.getId().value(),
            customer.getName().value(),
            customer.getEmail().value(),
            customer.getBirthdate().value(),
            customer.getCreatedAt()
        );

        customer.getAddresses().forEach(address -> {
            EntityDeliverAddressJpa entity = CustomerMapper.toEntity(address);
            entity.setCustomer(customerEntity);
            addressJpas.add(entity);
        });

        customerEntity.setAddress(addressJpas);
        return customerEntity;


    }

    private static DeliverAddress toDomain(EntityDeliverAddressJpa entity){
        return new DeliverAddress(
                entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getZipcode(),
                entity.getState(),
                entity.getReference(),
                entity.isActive(),
                entity.isMain()
        );
    }

    private static EntityDeliverAddressJpa toEntity(DeliverAddress domain){
        return new EntityDeliverAddressJpa(
                domain.getId().value(),
                domain.getStreet(),
                domain.getNumber(),
                domain.getNeighborhood(),
                domain.getCity(),
                domain.getZipcode().value(),
                domain.getState(),
                domain.getReference(),
                domain.isActive(),
                domain.isMain()
        );
    }
}
