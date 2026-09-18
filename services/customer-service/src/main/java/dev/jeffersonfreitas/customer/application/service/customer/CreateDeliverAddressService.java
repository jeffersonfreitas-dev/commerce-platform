package dev.jeffersonfreitas.customer.application.service.customer;

import dev.jeffersonfreitas.customer.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.customer.application.exception.NullObjectException;
import dev.jeffersonfreitas.customer.application.port.in.customer.OutputDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.InputCreateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.UCCreateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.application.port.out.customer.DeliverAddressRepository;
import dev.jeffersonfreitas.customer.domain.model.Customer;
import dev.jeffersonfreitas.customer.domain.model.DeliverAddress;

public class CreateDeliverAddressService implements UCCreateDeliverAddress{

	private final DeliverAddressRepository repository;
	private final CustomerRepository customerRepository;

	public CreateDeliverAddressService(DeliverAddressRepository repository, CustomerRepository customerRepository){
		this.repository = repository;
		this.customerRepository = customerRepository;
	}


	@Override
	public OutputDeliverAddress execute(String email, InputCreateDeliverAddress input) {
		if(input == null || email == null){
            throw new NullObjectException("O input e/ou e-mail da requisição não pode ser nulo");
        }

		Customer customer = customerRepository.getByEmail(email)
			.orElseThrow(() -> new CustomerNotFoundException("O cliente não foi encontrado na alteração do endereço"));

		DeliverAddress deliverAddress = InputCreateDeliverAddress.toDomain(input);
		DeliverAddress deliverAddressUpdated = repository.save(customer, deliverAddress);
		return OutputDeliverAddress.from(deliverAddressUpdated);

	}

}
