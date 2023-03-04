package com.example.reactive.repository;

import com.example.reactive.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, UUID> {
}
