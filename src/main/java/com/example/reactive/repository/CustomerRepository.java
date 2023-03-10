package com.example.reactive.repository;

import com.example.reactive.domain.entities.Customer;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, UUID> {
    @Query("{'name': '?0'}")
    Flux<Customer> findByName(String name);
}
