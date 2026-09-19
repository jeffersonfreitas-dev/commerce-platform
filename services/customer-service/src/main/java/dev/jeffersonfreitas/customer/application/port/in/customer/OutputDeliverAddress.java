package dev.jeffersonfreitas.customer.application.port.in.customer;

import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

import java.util.ArrayList;
import java.util.List;

public record OutputDeliverAddress(
    String id,
    String street,
    String number,
    String neighborhood,
    String city,
    String zipcode,
    String state,
    String reference,
    boolean active
) {

    public static List<OutputDeliverAddress> from(Customer customer) {
        if(customer == null || customer.getAddresses() == null || customer.getAddresses().isEmpty()){
            return new ArrayList<>();
        }
        return customer.getAddresses().stream().map(OutputDeliverAddress::from).toList();
    }

    public static OutputDeliverAddress from(DeliverAddress address){
        return new OutputDeliverAddress(address.getId().value(), address.getStreet(), address.getNumber(), 
            address.getNeighborhood(), address.getCity(), address.getZipcode().value(), address.getState(), address.getReference(), address.isActive());
    }
}
