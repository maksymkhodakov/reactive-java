package com.example.reactive.service.implementations;

import com.example.reactive.domain.dto.StudentDTO;
import com.example.reactive.domain.entities.Student;
import com.example.reactive.repository.StudentRepository;
import com.example.reactive.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Flux<StudentDTO> findStudentsByName(String name) {
        return nonNull(name) ?
                studentRepository
                        .findByName(name)
                        .map(StudentDTO::new) :
                studentRepository
                        .findAll()
                        .map(StudentDTO::new);
    }

    @Override
    public Mono<StudentDTO> findStudentById(long id) {
        return studentRepository
                .findById(id)
                .map(StudentDTO::new);
    }

    @Override
    public Mono<StudentDTO> addNewStudent(StudentDTO student) {
        return studentRepository
                .save(new Student(student))
                .map(StudentDTO::new);
    }

    @Override
    public Mono<StudentDTO> updateStudent(long id, StudentDTO student) {
        return studentRepository
                .findById(id)
                .flatMap(s -> {
                    student.setId(s.getId());
                    return studentRepository
                            .save(new Student(student))
                            .map(StudentDTO::new);
                });
    }

    @Override
    public Mono<Void> deleteStudent(StudentDTO student) {
        return studentRepository
                .delete(new Student(student));
    }
}
