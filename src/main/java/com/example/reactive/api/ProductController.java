package com.example.reactive.api;

import com.example.reactive.domain.dto.ProductDTO;
import com.example.reactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public Flux<ProductDTO> all() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ProductDTO> getById(@PathVariable String id) {
        return productService.getById(id);
    }

    @GetMapping("/in-range")
    public Flux<ProductDTO> getProductsInRange(@RequestParam("min") double min,
                                               @RequestParam("max") double max) {
        return productService.getInRange(min, max);
    }

    @PostMapping("/save")
    public Mono<ProductDTO> save(Mono<ProductDTO> product) {
        return productService.save(product);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDTO> update(@PathVariable("id") String id,
                                   @RequestBody Mono<ProductDTO> product) {
        return productService.update(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return productService.deleteProduct(id);
    }
}
