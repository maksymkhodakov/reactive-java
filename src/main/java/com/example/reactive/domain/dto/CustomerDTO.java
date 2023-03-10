package com.example.reactive.domain.dto;

import com.example.reactive.domain.interfces.ICustomer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO implements ICustomer {
    private UUID id;

    private String name;

    private String email;

    private List<ProductDTO> products;

    public CustomerDTO(ICustomer iCustomer) {
        this.id = iCustomer.getId();
        this.name = iCustomer.getName();
        this.email = iCustomer.getEmail();
        this.products = isNull(iCustomer.getProducts()) ? null :
                iCustomer.getProducts()
                        .stream()
                        .map(ProductDTO::new)
                        .toList();
    }
}
