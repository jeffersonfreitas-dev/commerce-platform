package dev.jeffersonfreitas.customer.application.port.out.customer;

import java.util.Optional;

import dev.jeffersonfreitas.customer.domain.model.Customer;

public interface CustomerRepository {

    boolean existsByEmail(String email);
    Customer save(Customer customer);
    Optional<Customer> getByEmail(String email);
}
