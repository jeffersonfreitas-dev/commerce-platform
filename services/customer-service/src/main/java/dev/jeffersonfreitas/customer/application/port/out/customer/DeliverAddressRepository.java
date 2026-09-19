package dev.jeffersonfreitas.customer.application.port.out.customer;

import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

import java.util.Optional;

public interface DeliverAddressRepository {

    DeliverAddress save(Customer customer, DeliverAddress address);
    Optional<DeliverAddress> get(String addressId);
}
