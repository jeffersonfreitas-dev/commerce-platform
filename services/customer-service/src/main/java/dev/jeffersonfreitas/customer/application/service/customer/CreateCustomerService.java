package dev.jeffersonfreitas.customer.application.service.customer;

import dev.jeffersonfreitas.customer.application.exception.NullObjectException;
import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.InputCreateCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.InputCreateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.UCCreateCustomer;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.domain.exception.CustomerAlreadyExistsException;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

public class CreateCustomerService implements UCCreateCustomer {

    private final CustomerRepository customerRepository;

    public CreateCustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public OutputCustomer execute(InputCreateCustomer input) {
        if(input == null){
            throw new NullObjectException("O input não pode ser nulo ao cadastrar um cliente");
        }
        if(customerRepository.existsByEmail(input.email())){
            throw new CustomerAlreadyExistsException("O e-mail informado já foi cadastrado");
        }
        DeliverAddress deliverAddress = InputCreateDeliverAddress.toDomain(input.address());
        Customer customer = new Customer(input.name(), input.email(), input.birthdate(), deliverAddress);
        Customer savedCustomer = customerRepository.save(customer);
        return OutputCustomer.from(savedCustomer);
    }
}
