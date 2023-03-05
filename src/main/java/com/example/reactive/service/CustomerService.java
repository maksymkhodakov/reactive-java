package com.example.reactive.service;

import com.example.reactive.domain.entities.Customer;
import com.example.reactive.domain.entities.CustomerEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<Customer> getAll();
    Mono<Customer> getById(String id);
    Flux<CustomerEvent> getEvents(String id);
}
