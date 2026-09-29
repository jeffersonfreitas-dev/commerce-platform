package dev.jeffersonfreitas.order.infra.out.customerservice;

import dev.jeffersonfreitas.order.application.exception.CustomerNotFoundException;
import dev.jeffersonfreitas.order.application.port.out.customerservice.CustomerGateway;
import dev.jeffersonfreitas.order.application.port.out.customerservice.CustomerOutput;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.ClientAttributes.clientRegistrationId;

@Component
public class CustomerHttpClient implements CustomerGateway {

    private final RestClient restClient;

    public CustomerHttpClient(RestClient restClient){
        this.restClient = restClient;
    }

    @Override
    public CustomerOutput findById(String id) {
        return restClient
                .get()
                .uri("/customers/{id}", id)
                .attributes(clientRegistrationId("customer-service"))
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        (request, response) -> {
                            throw new CustomerNotFoundException();
                        }
                )
                .body(CustomerOutput.class);
    }
}
