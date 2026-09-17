package dev.jeffersonfreitas.customer.infra.out.persistence.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import dev.jeffersonfreitas.customer.application.dto.PageGeneric;
import dev.jeffersonfreitas.customer.application.dto.PageableRequest;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.infra.out.PageRequestMapper;

import java.util.List;
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
        CustomerJpaEntity entity = CustomerMapper.toEntity(customer);
        entity = customerJpaRepository.save(entity);
        return CustomerMapper.toDomain(entity);
    }

    @Override
    public Optional<Customer> getByEmail(String email) {
        return customerJpaRepository.findByEmail(email).map(CustomerMapper::toDomain);
    }
}
