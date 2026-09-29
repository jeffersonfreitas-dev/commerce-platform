package dev.jeffersonfreitas.customer.application.port.in.customer.get;

import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;

public interface UCGetByIdCustomer {

    OutputCustomer execute(String id);
}
