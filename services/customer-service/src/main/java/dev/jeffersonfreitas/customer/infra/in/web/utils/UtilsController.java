package dev.jeffersonfreitas.customer.infra.in.web.utils;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/utils")
public class UtilsController {

    @GetMapping("/me")
    public String me(@AuthenticationPrincipal Jwt jwt) {
        return "Usuário autenticado: " + jwt.getSubject()
                + " | Email: " + jwt.getClaimAsString("email");
    }
}
