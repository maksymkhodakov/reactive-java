package com.example.reactive.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface StudentHandler {
    Mono<ServerResponse> getStudent(ServerRequest serverRequest);
    Mono<ServerResponse> listStudents(ServerRequest serverRequest);
    Mono<ServerResponse> addNewStudent(ServerRequest serverRequest);
    Mono<ServerResponse> updateStudent(ServerRequest serverRequest);
    Mono<ServerResponse> deleteStudent(ServerRequest serverRequest);
}
