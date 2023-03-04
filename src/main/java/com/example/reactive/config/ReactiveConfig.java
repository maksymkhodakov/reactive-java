package com.example.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class ReactiveConfig {
    @Bean
    public RouterFunction<?> routerFunction(RouterHandlers routersHandlers) {
        return RouterFunctions.route(RequestPredicates.GET("/api/customers/all"), routersHandlers::getAll)
                .andRoute(RequestPredicates.GET("/api/customers/{id}"), routersHandlers::getId)
                .andRoute(RequestPredicates.GET("/api/customers/{id}/events"), routersHandlers::getEvents);
    }
}
