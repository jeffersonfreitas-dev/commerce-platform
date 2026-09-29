package dev.jeffersonfreitas.customer.application.port.out.customer;

import dev.jeffersonfreitas.customer.domain.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    boolean existsByEmail(String email);
    Customer save(Customer customer);
    Optional<Customer> getByEmail(String email);
    Optional<Customer> getById(String id);
}
