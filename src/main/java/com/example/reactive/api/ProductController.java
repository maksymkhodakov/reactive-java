package com.example.reactive.api;

import com.example.reactive.domain.dto.ProductDTO;
import com.example.reactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public Flux<ResponseEntity<ProductDTO>> all() {
        return productService
                .getAll()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> getById(@PathVariable String id) {
        return productService
                .getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/in-range")
    public Flux<ResponseEntity<ProductDTO>> getProductsInRange(@RequestParam("min") double min,
                                               @RequestParam("max") double max) {
        return productService
                .getInRange(min, max)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<ProductDTO>> save(Mono<ProductDTO> product) {
        return productService
                .save(product)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<ProductDTO>> update(@PathVariable("id") String id,
                                   @RequestBody Mono<ProductDTO> product) {
        return productService
                .update(id, product)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return productService.deleteProduct(id)
                .then(Mono.just(new ResponseEntity<>(HttpStatus.OK)));
    }
}
