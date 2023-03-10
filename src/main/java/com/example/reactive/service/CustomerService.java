package com.example.reactive.service;

import com.example.reactive.domain.dto.CustomerDTO;
import com.example.reactive.domain.entities.CustomerEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<CustomerDTO> getAll();
    Mono<CustomerDTO> getById(String id);
    Flux<CustomerEvent> getEvents(String id);
    Flux<CustomerDTO> getByName(String name);
    Mono<Void> delete(String id);
}
