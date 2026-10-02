package dev.jeffersonfreitas.customer.infra.in.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class KeycloakTokenService {

    private final RestClient keycloakRestClient;

    @Value("${keycloak.client-id:customer-service}")
    private String clientId;

    @Value("${KEYCLOAK_CLIENT_SECRET:}")
    private String clientSecret;

    @Value("${keycloak.scope:openid profile email}")
    private String scope;

    // Cache simples em memória
    private volatile String cachedToken;
    private volatile Instant expiresAt = Instant.EPOCH;
    private final ReentrantLock lock = new ReentrantLock();

    public KeycloakTokenService(RestClient keycloakRestClient) {
        this.keycloakRestClient = keycloakRestClient;
    }

    public String getAccessToken() {
        // Margem de segurança de 30s antes de expirar
        if (cachedToken != null && Instant.now().isBefore(expiresAt.minusSeconds(30))) {
            return cachedToken;
        }

        lock.lock();
        try {
            // Double-check após adquirir o lock
            if (cachedToken != null && Instant.now().isBefore(expiresAt.minusSeconds(30))) {
                return cachedToken;
            }

            TokenResponse response = requestNewToken();
            this.cachedToken = response.accessToken();
            this.expiresAt = Instant.now().plusSeconds(response.expiresIn());
            return cachedToken;
        } finally {
            lock.unlock();
        }
    }

    private TokenResponse requestNewToken() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("scope", scope);

        return keycloakRestClient.post()
                .uri("/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(TokenResponse.class);
    }
}
