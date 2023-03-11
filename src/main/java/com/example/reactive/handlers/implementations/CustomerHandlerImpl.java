package com.example.reactive.handlers.implementations;

import com.example.reactive.domain.entities.Customer;
import com.example.reactive.domain.entities.CustomerEvent;
import com.example.reactive.handlers.CustomerHandler;
import com.example.reactive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerHandlerImpl implements CustomerHandler {
    private final CustomerService customerService;

    @Override
    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(
                        customerService.getAll(), Customer.class
                );
    }

    @Override
    public Mono<ServerResponse> getId(ServerRequest serverRequest) {
        final String customerId = serverRequest.pathVariable("id");
        return ServerResponse
                .ok()
                .body(customerService.getById(customerId), Customer.class);
    }

    @Override
    public Mono<ServerResponse> getEvents(ServerRequest serverRequest) {
        final String customerId = serverRequest.pathVariable("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerService.getEvents(customerId), CustomerEvent.class);
    }
}
