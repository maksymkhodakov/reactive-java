package com.example.reactive.repository;

import com.example.reactive.domain.entities.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
    Flux<Student> findByName(String name);
}
