package com.example.reactive.api;

import com.example.reactive.domain.dto.CustomerDTO;
import com.example.reactive.domain.entities.CustomerEvent;
import com.example.reactive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/all")
    public Flux<CustomerDTO> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/name/{name}")
    public Flux<CustomerDTO> getByName(@PathVariable String name) {
        return customerService.getByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return customerService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<CustomerDTO> getById(@PathVariable String id) {
        return customerService.getById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CustomerEvent> getEvents(@PathVariable String id) {
        return customerService.getEvents(id);
    }
}
