package com.example.reactive.service;

import com.example.reactive.domain.dto.StudentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<StudentDTO> findStudentsByName(String name);
    Mono<StudentDTO> findStudentById(long id);
    Mono<StudentDTO> addNewStudent(StudentDTO student);
    Mono<StudentDTO> updateStudent(long id, StudentDTO student);
    Mono<Void> deleteStudent(StudentDTO student);
}
