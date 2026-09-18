package dev.jeffersonfreitas.customer.infra.in.web.customer;

import dev.jeffersonfreitas.customer.application.port.in.customer.update.InputUpdateDeliverAddress;

public record RequestUpdateDeliverAddress(
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

    public static InputUpdateDeliverAddress toInput(RequestUpdateDeliverAddress request) {
        return new InputUpdateDeliverAddress(request.id, request.street, request.number, request.neighborhood, 
            request.city, request.zipcode, request.state, request.reference, request.main);
    }
}
