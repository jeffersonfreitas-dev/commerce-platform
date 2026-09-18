package dev.jeffersonfreitas.customer.application.service.customer;

import dev.jeffersonfreitas.customer.application.exception.AddressNotBelongException;
import dev.jeffersonfreitas.customer.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.customer.application.port.in.customer.OutputDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.update.InputUpdateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.update.UCUpdateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.application.port.out.customer.DeliverAddressRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

public class UpdateDeliverAddressService implements UCUpdateDeliverAddress {

    private final DeliverAddressRepository repository;
    private final CustomerRepository customerRepository;

    public UpdateDeliverAddressService(DeliverAddressRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OutputDeliverAddress execute(String email, InputUpdateDeliverAddress input) {
        Customer customer = customerRepository.getByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("E-mail não encontrado para realizar a atualização do cliente"));

        boolean notBelongCustomer = customer.getAddresses().stream().noneMatch(address -> address.getId().value().equals(input.id()));
        if(notBelongCustomer){
            throw new AddressNotBelongException("O endereço informado não pertence ao cliente");
        }

        DeliverAddress deliverAddress = InputUpdateDeliverAddress.toDomain(input);
        DeliverAddress deliverAddressUpdated = repository.save(customer, deliverAddress);
        return OutputDeliverAddress.from(deliverAddressUpdated);
    }
}
