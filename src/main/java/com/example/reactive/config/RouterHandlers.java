package com.example.reactive.config;

import com.example.reactive.domain.entities.Customer;
import com.example.reactive.domain.entities.CustomerEvent;
import com.example.reactive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RouterHandlers {
    private final CustomerService customerService;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(
                        customerService.getAll(), Customer.class
                );
    }

    public Mono<ServerResponse> getId(ServerRequest serverRequest) {
        final String customerId = serverRequest.pathVariable("id");
        return ServerResponse
                .ok()
                .body(customerService.getById(customerId), Customer.class);
    }

    public Mono<ServerResponse> getEvents(ServerRequest serverRequest) {
        final String customerId = serverRequest.pathVariable("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerService.getEvents(customerId), CustomerEvent.class);
    }
}
