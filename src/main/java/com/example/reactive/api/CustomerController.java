package com.example.reactive.api;

import com.example.reactive.domain.dto.CustomerDTO;
import com.example.reactive.domain.entities.CustomerEvent;
import com.example.reactive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/all")
    public Flux<ResponseEntity<CustomerDTO>> getAll() {
        return customerService
                .getAll()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public Flux<ResponseEntity<CustomerDTO>> getByName(@PathVariable String name) {
        return customerService
                .getByName(name)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return customerService.delete(id)
                .then(Mono.just(new ResponseEntity<>(HttpStatus.OK)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerDTO>> getById(@PathVariable String id) {
        return customerService
                .getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ResponseEntity<CustomerEvent>> getEvents(@PathVariable String id) {
        return customerService
                .getEvents(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
