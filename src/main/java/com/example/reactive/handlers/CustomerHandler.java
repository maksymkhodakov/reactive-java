package com.example.reactive.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface CustomerHandler {
    Mono<ServerResponse> getAll(ServerRequest serverRequest);
    Mono<ServerResponse> getId(ServerRequest serverRequest);
    Mono<ServerResponse> getEvents(ServerRequest serverRequest);
}
