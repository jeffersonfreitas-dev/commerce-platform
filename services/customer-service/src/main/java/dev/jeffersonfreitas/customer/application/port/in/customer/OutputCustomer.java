package dev.jeffersonfreitas.customer.application.port.in.customer;

import java.time.LocalDate;
import java.util.List;

import dev.jeffersonfreitas.customer.domain.model.Customer;

public record OutputCustomer(
        String name,
        String email,
        LocalDate birthdate,
        List<OutputDeliverAddress> addresses
) {
    public static OutputCustomer from(Customer customer) {
        List<OutputDeliverAddress> addresses = OutputDeliverAddress.from(customer);
        return new OutputCustomer(
                customer.getName().value(),
                customer.getEmail().value(),
                customer.getBirthdate().value(),
                addresses
                );
    }
}
