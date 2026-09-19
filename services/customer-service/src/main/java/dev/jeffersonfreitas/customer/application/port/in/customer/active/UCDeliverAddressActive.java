package dev.jeffersonfreitas.customer.application.port.in.customer.active;

public interface UCDeliverAddressActive {
    void execute(String email, String addressId);
}
