package dev.jeffersonfreitas.customer.application.port.in.customer.update;

import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

public record InputUpdateDeliverAddress(
    String id,
    String street,
    String number,
    String neighborhood,
    String city,
    String zipcode,
    String state,
    String reference,
    boolean main
) {

    public static DeliverAddress toDomain(InputUpdateDeliverAddress address){
        return new DeliverAddress(address.id, address.street, address.number, address.neighborhood, address.city, address.zipcode, address.state, address.reference, address.main);
    }

}
