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

import java.util.Optional;

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

        Optional<DeliverAddress> addressSaved = customer.getAddresses().stream()
                .filter(address -> address.getId().value().equals(input.id())).findFirst();
        if(addressSaved.isEmpty()){
            throw new AddressNotBelongException("O endereço informado não pertence ao cliente");
        }

        DeliverAddress deliverAddress = InputUpdateDeliverAddress.toDomain(input, addressSaved.get().isActive());
        DeliverAddress deliverAddressUpdated = repository.save(customer, deliverAddress);
        return OutputDeliverAddress.from(deliverAddressUpdated);
    }
}
