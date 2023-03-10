package com.example.reactive.domain.entities;

import com.example.reactive.domain.interfces.ICustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Customer implements ICustomer {
    @Id
    private UUID id;

    private String name;

    private String email;

    @DocumentReference
    private List<Product> products;

    public Customer(ICustomer iCustomer) {
        this.id = iCustomer.getId();
        this.name = iCustomer.getName();
        this.email = iCustomer.getEmail();
        this.products = isNull(iCustomer.getProducts()) ? null :
                iCustomer.getProducts()
                        .stream()
                        .map(Product::new)
                        .toList();
    }
}
