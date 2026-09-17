package dev.jeffersonfreitas.customer.application.port.in.customer;

import java.util.ArrayList;
import java.util.List;

import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

public record OutputDeliverAddress(
    String street,
    String number,
    String neighborhood,
    String city,
    String zipcode,
    String state,
    String reference
) {

    public static List<OutputDeliverAddress> from(Customer customer) {
        if(customer == null || customer.getAddresses() == null || customer.getAddresses().isEmpty()){
            return new ArrayList<>();
        }
        return customer.getAddresses().stream().map(OutputDeliverAddress::from).toList();
    }

    private static OutputDeliverAddress from(DeliverAddress address){
        return new OutputDeliverAddress(address.getStreet(), address.getNumber(), 
            address.getNeighborhood(), address.getCity(), address.getZipcode().value(), address.getState(), address.getReference());
    }
}
