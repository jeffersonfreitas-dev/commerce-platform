package dev.jeffersonfreitas.customer.infra.in.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/token")
public class KeycloakController {

    private final KeycloakTokenService tokenService;

    public KeycloakController(KeycloakTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    public String fazerAlgo() {
        String token = tokenService.getAccessToken();
        return token;
    }
}
