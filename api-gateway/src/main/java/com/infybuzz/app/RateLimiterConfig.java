package com.infybuzz.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RateLimiterConfig {
    Logger logger = LoggerFactory.getLogger(RateLimiterConfig.class);

    @Bean
    public KeyResolver keyResolver() {
        return exchange -> {

            String hostAddress = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress();
            logger.info("Host address = " + hostAddress);
            return Mono.just(hostAddress);
        };
    }

}
