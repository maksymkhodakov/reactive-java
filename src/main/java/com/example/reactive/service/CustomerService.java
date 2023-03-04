package com.example.reactive.service;

import com.example.reactive.domain.Customer;
import com.example.reactive.domain.CustomerEvent;
import com.example.reactive.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Flux<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Mono<Customer> getById(@PathVariable String id) {
        return customerRepository.findById(UUID.fromString(id));
    }

    public Flux<CustomerEvent> getEvents(@PathVariable String id) {
        return customerRepository
                .findById(UUID.fromString(id))
                .flatMapMany(customer -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                    Flux<CustomerEvent> customerEventFlux =
                            Flux.fromStream(
                                    Stream.generate(() -> new CustomerEvent(customer, new Date()))
                            );
                    return Flux.zip(interval, customerEventFlux)
                            .map(Tuple2::getT2);
                });
    }
}
