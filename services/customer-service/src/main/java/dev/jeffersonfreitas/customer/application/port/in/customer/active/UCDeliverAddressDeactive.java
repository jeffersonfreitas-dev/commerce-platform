package dev.jeffersonfreitas.customer.application.port.in.customer.active;

public interface UCDeliverAddressDeactive {

    void execute(String email, String addressId);
}
