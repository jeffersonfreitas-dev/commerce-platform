package dev.jeffersonfreitas.customer.application.port.in.customer.get;

import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;

public interface UCGetCustomer {

    OutputCustomer execute(String email);
}
