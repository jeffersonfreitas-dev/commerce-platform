package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import dev.jeffersonfreitas.customer.application.port.out.customer.DeliverAddressRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

    @Override
    public Optional<DeliverAddress> get(String addressId) {
        return repository.findById(addressId).map(CustomerMapper::toDomain);
    }

}
