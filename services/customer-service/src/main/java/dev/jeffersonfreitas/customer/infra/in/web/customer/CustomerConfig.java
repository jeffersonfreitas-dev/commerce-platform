package dev.jeffersonfreitas.customer.infra.in.web.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.jeffersonfreitas.customer.application.port.in.customer.create.UCCreateCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.create.UCCreateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.in.customer.get.UCGetCustomer;
import dev.jeffersonfreitas.customer.application.port.in.customer.update.UCUpdateDeliverAddress;
import dev.jeffersonfreitas.customer.application.port.out.customer.CustomerRepository;
import dev.jeffersonfreitas.customer.application.port.out.customer.DeliverAddressRepository;
import dev.jeffersonfreitas.customer.application.service.customer.*;

@Configuration
public class CustomerConfig {

    @Bean
    UCCreateCustomer ucCreateCustomer(CustomerRepository customerRepository){
        return new CreateCustomerService(customerRepository);
    }

    @Bean
    UCGetCustomer ucGetCustomer(CustomerRepository customerRepository){
        return new GetCustomerService(customerRepository);
    }

    @Bean
    UCUpdateDeliverAddress ucUpdateDeliverAddress(DeliverAddressRepository repository, CustomerRepository customerRepository){
        return new UpdateDeliverAddressService(repository, customerRepository);
    }

    @Bean
    UCCreateDeliverAddress ucCreateDeliverAddress(DeliverAddressRepository repository, CustomerRepository customerRepository){
        return new CreateDeliverAddressService(repository, customerRepository);
    }    
}
