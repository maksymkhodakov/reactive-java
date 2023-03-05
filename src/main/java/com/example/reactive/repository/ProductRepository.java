package com.example.reactive.repository;

import com.example.reactive.domain.dto.ProductDTO;
import com.example.reactive.domain.entities.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<ProductDTO> findByPriceBetween(Range<Double> priceRange);
}
