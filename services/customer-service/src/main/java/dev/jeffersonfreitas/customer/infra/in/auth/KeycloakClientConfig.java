package dev.jeffersonfreitas.customer.infra.in.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class KeycloakClientConfig {

    @Value("${keycloak.base-url:http://keycloak:8080}")
    private String keycloakBaseUrl;

    @Value("${keycloak.realm:commerce-platform}")
    private String realm;

    @Bean
    public RestClient keycloakRestClient() {
        return RestClient.builder()
                .baseUrl(keycloakBaseUrl + "/realms/" + realm + "/protocol/openid-connect")
                .defaultHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
    }
}
