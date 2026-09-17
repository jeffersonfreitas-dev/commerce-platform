package dev.jeffersonfreitas.customer.application.port.in.customer.create;

import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;

public interface UCCreateCustomer {

    OutputCustomer execute(InputCreateCustomer input);
}
