package dev.jeffersonfreitas.customer.application.port.in.customer.create;

import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

public record InputCreateDeliverAddress(
    String street,
    String number,
    String neighborhood,
    String city,
    String zipcode,
    String state,
    String reference
) {

    public static DeliverAddress toDomain(InputCreateDeliverAddress address){
        return new DeliverAddress(address.street, address.number, address.neighborhood, address.city, address.zipcode, address.state, address.reference);
    }

}
