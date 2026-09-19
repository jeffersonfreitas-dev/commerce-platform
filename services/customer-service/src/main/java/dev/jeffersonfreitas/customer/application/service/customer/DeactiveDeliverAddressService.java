package dev.jeffersonfreitas.customer.application.service.customer;

import dev.jeffersonfreitas.customer.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.customer.application.exception.DeliverAddressNotFoundException;
import dev.jeffersonfreitas.customer.application.exception.NullObjectException;
import dev.jeffersonfreitas.customer.application.port.in.customer.active.UCDeliverAddressDeactive;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.application.port.out.customer.DeliverAddressRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

public class DeactiveDeliverAddressService implements UCDeliverAddressDeactive {

    private final DeliverAddressRepository repository;
    private final CustomerRepository customerRepository;

    public DeactiveDeliverAddressService(DeliverAddressRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void execute(String email, String addressId) {
        if(email == null || addressId == null){
            throw new NullObjectException("O e-mail e/ou código do endereço não podem ser nulos");
        }

        Customer customerExists = customerRepository.getByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("O cliente não foi encontrado com o e-mail informado"));

        DeliverAddress deliverAddress = repository.get(addressId)
                        .orElseThrow(() -> new DeliverAddressNotFoundException("O endereço não foi encontrado com o código informado"));
        deliverAddress.deactive();
        repository.save(customerExists, deliverAddress);
    }
}
