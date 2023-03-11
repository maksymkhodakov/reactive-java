package com.example.reactive.domain.entities;

import com.example.reactive.domain.interfces.IStudent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Student implements IStudent {
    @Id
    private Long id;
    private String name;
    private String address;
    private String email;

    public Student(IStudent iStudent) {
        this.id = iStudent.getId();
        this.name = iStudent.getName();
        this.address = iStudent.getAddress();
        this.email = iStudent.getEmail();
    }
}
