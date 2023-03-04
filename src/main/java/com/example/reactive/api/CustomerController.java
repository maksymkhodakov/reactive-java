package com.example.reactive.api;

import com.example.reactive.domain.Customer;
import com.example.reactive.domain.CustomerEvent;
import com.example.reactive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public Flux<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Customer> getById(@PathVariable String id) {
        return customerService.getById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CustomerEvent> getEvents(@PathVariable String id) {
        return customerService.getEvents(id);
    }
}
