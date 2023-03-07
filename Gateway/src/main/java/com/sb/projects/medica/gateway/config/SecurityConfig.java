package com.sb.projects.medica.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .csrf()
                .disable()
                .authorizeExchange(exchanges -> {
            exchanges.pathMatchers("/doctor/**", "/patient/**").authenticated();
            exchanges.pathMatchers("/auth/**").permitAll();
        }).oauth2Client().and().oauth2ResourceServer().jwt();
        return httpSecurity.build();
    }
}
