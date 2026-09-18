package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import org.springframework.stereotype.Component;

import dev.jeffersonfreitas.customer.application.port.out.customer.DeliverAddressRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

@Component 
public class RepositoryImplDeliverAddressJpa implements DeliverAddressRepository{

    private final RepositoryDeliverAddressJpa repository;

    public RepositoryImplDeliverAddressJpa(RepositoryDeliverAddressJpa repository){
        this.repository = repository;
    }

    @Override
    public DeliverAddress save(Customer customer, DeliverAddress address) {
        EntityDeliverAddressJpa entity = CustomerMapper.toEntity(address);
        EntityCustomerJpa customerJpa = CustomerMapper.toEntity(customer);
        entity.setCustomer(customerJpa);
        entity = repository.save(entity);
        return CustomerMapper.toDomain(entity);
    }

}
