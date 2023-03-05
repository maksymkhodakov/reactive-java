package com.example.reactive.service.implementations;

import com.example.reactive.domain.dto.ProductDTO;
import com.example.reactive.domain.entities.Product;
import com.example.reactive.repository.ProductRepository;
import com.example.reactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Flux<ProductDTO> getAll() {
        return productRepository.findAll().map(ProductDTO::new);
    }

    @Override
    public Mono<ProductDTO> getById(String id) {
        return productRepository.findById(id).map(ProductDTO::new);
    }

    @Override
    public Flux<ProductDTO> getInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    @Override
    public Mono<ProductDTO> save(Mono<ProductDTO> productDTO) {
        return productDTO
                .map(Product::new)
                .flatMap(productRepository::insert)
                .map(ProductDTO::new);
    }

    @Override
    public Mono<ProductDTO> update(String id, Mono<ProductDTO> productDTO) {
        return productRepository.findById(id)
                .flatMap(product -> productDTO.map(Product::new))
                .doOnNext(e -> e.setId(id))
                .flatMap(productRepository::save)
                .map(ProductDTO::new);
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
}
