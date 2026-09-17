package dev.jeffersonfreitas.customer.application.service.customer;

import dev.jeffersonfreitas.customer.application.exception.AddressNotBelongException;
import dev.jeffersonfreitas.customer.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.customer.application.port.in.customer.OutputCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.update.InputUpdateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.update.UCUpdateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

public class UpdateDeliverAddressService implements UCUpdateDeliverAddress {

    private final CustomerRepository customerRepository;

    public UpdateDeliverAddressService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public OutputCustomer execute(String email, InputUpdateDeliverAddress input) {
        Customer customer = customerRepository.getByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("E-mail não encontrado para realizar a atualização do cliente"));

        boolean notBelongCustomer = customer.getAddresses().stream().noneMatch(address -> address.getId().value().equals(input.id()));
        if(notBelongCustomer){
            throw new AddressNotBelongException("O endereço informado não pertence ao cliente");
        }

        DeliverAddress deliverAddress = InputUpdateDeliverAddress.toDomain(input);
        customer.getAddresses().add(deliverAddress);

        Customer customerUpdated = new Customer(customer.getId(), customer.getName(), customer.getBirthdate(), customer.getEmail(), customer.getCreatedAt(), customer.getAddresses());
        customerRepository.save(customerUpdated);
        return OutputCustomer.from(customerUpdated);
    }
}
