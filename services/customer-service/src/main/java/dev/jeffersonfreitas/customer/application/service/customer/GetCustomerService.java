package dev.jeffersonfreitas.customer.application.service.customer;

import dev.jeffersonfreitas.customer.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.get.UCGetCustomer;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;

public class GetCustomerService implements UCGetCustomer {

    private final CustomerRepository customerRepository;

    public GetCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public OutputCustomer execute(String email) {
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("O e-mail informado não pode ser nulo ou vazio");
        }
        Customer customer = customerRepository.getByEmail(email).orElseThrow(
                () -> new CustomerNotFoundException("Não existe cliente cadastrado com o e-mail informado"));
        return OutputCustomer.from(customer);
    }
}
