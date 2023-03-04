package com.example.reactive;

import com.example.reactive.domain.Customer;
import com.example.reactive.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class ReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }

    @Bean
    CommandLineRunner customers(CustomerRepository customerRepository) {
        return args -> {
            customerRepository
                    .deleteAll()
                    .subscribe(null, null, () -> {
                       Stream.of(
                               new Customer(UUID.randomUUID(), "Max1", "test1@test.com"),
                               new Customer(UUID.randomUUID(), "Max2", "test2@test.com"),
                               new Customer(UUID.randomUUID(), "Max3", "test3@test.com")
                               )
                               .forEach(c -> customerRepository
                                       .save(c)
                                       .subscribe(customer -> log.info(customer.toString())));
                    });
        };
    }
}
