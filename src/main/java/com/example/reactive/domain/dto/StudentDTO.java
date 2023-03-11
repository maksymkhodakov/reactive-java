package com.example.reactive.domain.dto;

import com.example.reactive.domain.interfces.IStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentDTO implements IStudent {
    private Long id;
    private String name;
    private String address;
    private String email;

    public StudentDTO(IStudent iStudent) {
        this.id = iStudent.getId();
        this.name = iStudent.getName();
        this.address = iStudent.getAddress();
        this.email = iStudent.getEmail();
    }
}
