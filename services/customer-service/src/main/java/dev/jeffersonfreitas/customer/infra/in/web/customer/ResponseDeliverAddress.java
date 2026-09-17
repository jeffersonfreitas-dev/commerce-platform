package dev.jeffersonfreitas.customer.infra.in.web.customer;

import java.util.List;
import dev.jeffersonfreitas.customer.application.port.in.customer.OutputDeliverAddress;

public record ResponseDeliverAddress(
    String street,
    String number,
    String neighborhood,
    String city,
    String zipcode,
    String state,
    String reference
) {

    public static List<ResponseDeliverAddress> from(List<OutputDeliverAddress> addresses) {
        return addresses.stream().map(ResponseDeliverAddress::from).toList();
    }

    private static ResponseDeliverAddress from(OutputDeliverAddress address){
        return new ResponseDeliverAddress(address.street(), address.number(), 
            address.neighborhood(), address.city(), address.zipcode(), address.state(), address.reference());
    }
}
