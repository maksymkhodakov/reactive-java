package com.example.reactive.repository;

import com.example.reactive.domain.dto.ProductDTO;
import com.example.reactive.domain.entities.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<ProductDTO> findByPriceBetween(Mono<Double> price, Mono<Double> price2);
}
