package com.example.reactive.service.implementations;

import com.example.reactive.domain.dto.CustomerDTO;
import com.example.reactive.domain.entities.CustomerEvent;
import com.example.reactive.repository.CustomerRepository;
import com.example.reactive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Flux<CustomerDTO> getAll() {
        return customerRepository
                .findAll()
                .map(CustomerDTO::new);
    }

    @Override
    public Mono<CustomerDTO> getById(String id) {
        return customerRepository
                .findById(UUID.fromString(id))
                .map(CustomerDTO::new);
    }

    @Override
    public Flux<CustomerEvent> getEvents(String id) {
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

    @Override
    public Flux<CustomerDTO> getByName(String name) {
        return customerRepository
                .findByName(name)
                .map(CustomerDTO::new);
    }

    @Override
    public Mono<Void> delete(String id) {
        return customerRepository.deleteById(UUID.fromString(id));
    }
}
