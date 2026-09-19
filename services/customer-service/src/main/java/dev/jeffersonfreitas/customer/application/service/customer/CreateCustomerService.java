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

        Customer customer = getCustomer(input);
        Customer savedCustomer = customerRepository.save(customer);
        return OutputCustomer.from(savedCustomer);
    }

    private static Customer getCustomer(InputCreateCustomer input) {
        if(input.address() == null){
            throw new NullObjectException("O endereço de entrega não pode ser nulo");
        }

        InputCreateDeliverAddress inputAddress = input.address();
        DeliverAddress deliverAddress = new DeliverAddress(
                inputAddress.street(),
                inputAddress.number(),
                inputAddress.neighborhood(),
                inputAddress.city(),
                inputAddress.zipcode(),
                inputAddress.state(),
                inputAddress.reference()
        );
        return new Customer(input.name(), input.email(), input.birthdate(), deliverAddress);
    }
}
