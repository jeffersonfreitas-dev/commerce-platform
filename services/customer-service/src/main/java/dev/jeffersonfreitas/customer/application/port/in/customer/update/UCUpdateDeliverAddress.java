package dev.jeffersonfreitas.customer.application.port.in.customer.update;

import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;

public interface UCUpdateDeliverAddress {

    OutputCustomer execute(String email, InputUpdateDeliverAddress input);

}
