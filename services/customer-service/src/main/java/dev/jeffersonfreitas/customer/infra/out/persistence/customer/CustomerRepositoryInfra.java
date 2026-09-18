package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryInfra implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryInfra(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }

    @Override
    public Customer save(Customer customer) {
        EntityCustomerJpa entity = CustomerMapper.toEntity(customer);
        entity = customerJpaRepository.save(entity);
        return CustomerMapper.toDomain(entity);
    }

    @Override
    public Optional<Customer> getByEmail(String email) {
        return customerJpaRepository.findByEmail(email).map(CustomerMapper::toDomain);
    }
}
