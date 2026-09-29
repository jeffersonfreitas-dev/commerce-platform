package dev.jeffersonfreitas.order.application.port.out.customerservice;

public interface CustomerGateway {
    CustomerOutput findById(String id);
}
