package dev.jeffersonfreitas.customer.application.port.in.customer.create;

import dev.jeffersonfreitas.customer.application.port.in.customer.OutputDeliverAddress;

public interface UCCreateDeliverAddress {
    OutputDeliverAddress execute(String email, InputCreateDeliverAddress input);
}
