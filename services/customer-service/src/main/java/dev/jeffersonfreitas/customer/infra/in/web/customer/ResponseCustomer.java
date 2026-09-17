package dev.jeffersonfreitas.customer.infra.in.web.customer;

import java.time.LocalDate;
import java.util.List;

import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;

public record ResponseCustomer(
        String name,
        String email,
        LocalDate birthdate,
        List<ResponseDeliverAddress> addresses
) {

    public static ResponseCustomer from(OutputCustomer output) {
        List<ResponseDeliverAddress> deliverAddresses = ResponseDeliverAddress.from(output.addresses());
        return new ResponseCustomer(output.name(), output.email(), output.birthdate(), deliverAddresses);
    }
}
