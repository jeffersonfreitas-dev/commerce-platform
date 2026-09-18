package dev.jeffersonfreitas.customer.application.port.in.customer.update;

import dev.jeffersonfreitas.customer.application.port.in.customer.OutputDeliverAddress;

public interface UCUpdateDeliverAddress {

    OutputDeliverAddress execute(String email, InputUpdateDeliverAddress input);

}
