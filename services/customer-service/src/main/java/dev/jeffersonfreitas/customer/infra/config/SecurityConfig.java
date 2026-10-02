package dev.jeffersonfreitas.customer.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String issuerUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/actuator/health").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }


//    @Bean
//    public JwtDecoder jwtDecoder(){
//        NimbusJwtDecoder decoder = JwtDecoders.fromIssuerLocation(issuerUri);
//        OAuth2TokenValidator<Jwt> serviceValidator = jwt -> {
//            String azp = jwt.getClaimAsString("azp");
//            if (!"order-service".equals(azp)) {
//                return OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token"));
//            }
//            return OAuth2TokenValidatorResult.success();
//        };
//        decoder.setJwtValidator(serviceValidator);
//        return decoder;
//    }
}
