package com.example.reactive.handlers.implementations;

import com.example.reactive.domain.dto.StudentDTO;
import com.example.reactive.handlers.StudentHandler;
import com.example.reactive.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class StudentHandlerImpl implements StudentHandler {
    private final StudentService studentService;

    @Override
    public Mono<ServerResponse> getStudent(ServerRequest serverRequest) {
        final Mono<StudentDTO> studentDtoMono = studentService
                .findStudentById(Long.parseLong(serverRequest.pathVariable("id")))
                .map(StudentDTO::new);
        return studentDtoMono
                .flatMap(student -> ServerResponse.ok()
                        .body(fromValue(student)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Override
    public Mono<ServerResponse> listStudents(ServerRequest serverRequest) {
        final String name = serverRequest.queryParam("name").orElse(null);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.findStudentsByName(name), StudentDTO.class);
    }

    @Override
    public Mono<ServerResponse> addNewStudent(ServerRequest serverRequest) {
        final Mono<StudentDTO> studentMono = serverRequest.bodyToMono(StudentDTO.class);
        return studentMono.flatMap(student ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(studentService.addNewStudent(student), StudentDTO.class));
    }

    @Override
    public Mono<ServerResponse> updateStudent(ServerRequest serverRequest) {
        final long studentId = Long.parseLong(serverRequest.pathVariable("id"));
        final Mono<StudentDTO> studentMono = serverRequest.bodyToMono(StudentDTO.class);

        return studentMono.flatMap(student ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(studentService.updateStudent(studentId, student), StudentDTO.class));
    }

    @Override
    public Mono<ServerResponse> deleteStudent(ServerRequest serverRequest) {
        final long studentId = Long.parseLong(serverRequest.pathVariable("id"));
        return studentService
                .findStudentById(studentId)
                .flatMap(s -> ServerResponse.noContent().build(studentService.deleteStudent(s)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
