package dev.jeffersonfreitas.order.infra.out.customerservice;

import dev.jeffersonfreitas.order.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.order.application.exception.CustomerServiceUnavailableException;
import dev.jeffersonfreitas.order.application.port.out.customerservice.CustomerGateway;
import dev.jeffersonfreitas.order.application.port.out.customerservice.CustomerOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.retry.RetryException;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.ClientAttributes.clientRegistrationId;

@Component
public class CustomerHttpClient implements CustomerGateway {

    private static final Logger log = LoggerFactory.getLogger(CustomerHttpClient.class);

    private final RestClient restClient;
    private final RetryTemplate retryTemplate;

    public CustomerHttpClient(RestClient restClient, RetryTemplate retryTemplate){
        this.restClient = restClient;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public CustomerOutput findById(String id) {
        try {
            return retryTemplate.execute(() -> callCustomerServiceFindById(id));
        }catch (RetryException e){
            throw new CustomerServiceUnavailableException();
        }
    }

    private CustomerOutput callCustomerServiceFindById(String id) {
        return restClient
            .get()
            .uri("/customers/{id}", id)
            .attributes(clientRegistrationId("customer-service"))
            .retrieve()
            .onStatus(status -> status.value() == 404,
                    (request, response) -> {
                        throw new CustomerNotFoundException();
                    }
            ).body(CustomerOutput.class);
    }
}
