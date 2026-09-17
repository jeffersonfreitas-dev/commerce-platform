package dev.jeffersonfreitas.customer.infra.in.web.customer;

import dev.jeffersonfreitas.customer.application.port.in.customer.create.InputCreateDeliverAddress;

public record RequestCreateDeliverAddress(
    String street,
    String number,
    String neighborhood,
    String city,
    String zipcode,
    String state,
    String reference,
    boolean main    
) {

    public static InputCreateDeliverAddress toInput(RequestCreateDeliverAddress address) {
        return new InputCreateDeliverAddress(address.street, address.number, address.neighborhood, address.city, address.zipcode, address.state, address.reference, address.main);
    }

}
