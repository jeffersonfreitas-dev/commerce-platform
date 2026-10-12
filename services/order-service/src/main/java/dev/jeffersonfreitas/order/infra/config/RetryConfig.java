package dev.jeffersonfreitas.order.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.time.Duration;

@Configuration
public class RetryConfig {

    @Bean
    RetryTemplate customerServiceRetryTemplate(){
        var retryPolicy = RetryPolicy.builder()
                .includes(ResourceAccessException.class, HttpServerErrorException.class)
                .maxRetries(2)
                .delay(Duration.ofMillis(200))
                .maxDelay(Duration.ofSeconds(2))
                .build();
        return new RetryTemplate(retryPolicy);
    }
}
