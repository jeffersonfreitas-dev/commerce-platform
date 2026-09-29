package dev.jeffersonfreitas.customer.application.service.customer;

import dev.jeffersonfreitas.customer.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.get.UCGetByIdCustomer;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;

public class GetByIdCustomerService implements UCGetByIdCustomer {

    private final CustomerRepository customerRepository;

    public GetByIdCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public OutputCustomer execute(String id) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("O id informado não pode ser nulo ou vazio");
        }
        Customer customer = customerRepository.getById(id).orElseThrow(
                () -> new CustomerNotFoundException("Não existe cliente cadastrado com o id informado"));
        return OutputCustomer.from(customer);
    }
}
