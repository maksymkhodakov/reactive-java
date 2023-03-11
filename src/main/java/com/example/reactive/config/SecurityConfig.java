package com.example.reactive.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final EncryptionConfig encryptionConfig;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Value("${USER_PASSWORD}")
    private String userPassword;

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {

        UserDetails user = User
                .withUsername("user")
                .password(encryptionConfig.passwordEncoder().encode(userPassword))
                .roles("USER")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(encryptionConfig.passwordEncoder().encode(adminPassword))
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(user, admin);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("api/students/admin")
                .hasAuthority("ROLE_ADMIN")
                .anyExchange()
                .authenticated()
                .and().httpBasic()
                .and().build();
    }
}
