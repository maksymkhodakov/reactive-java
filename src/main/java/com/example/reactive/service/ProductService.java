package com.example.reactive.service;

import com.example.reactive.domain.dto.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDTO> getAll();
    Mono<ProductDTO> getById(String id);
    Flux<ProductDTO> getInRange(double min, double max);
    Mono<ProductDTO> save(Mono<ProductDTO> productDTO);
    Mono<ProductDTO> update(String id, Mono<ProductDTO> productDTO);
    Mono<Void> deleteProduct(String id);
}
