package com.example.reactive.config;

import com.example.reactive.domain.Customer;
import com.example.reactive.domain.CustomerEvent;
import com.example.reactive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RouterHandlers {
    private final CustomerService customerService;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(
                        customerService, Customer.class
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
