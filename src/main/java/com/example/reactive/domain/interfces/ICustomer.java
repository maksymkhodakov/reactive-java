package com.example.reactive.domain.interfces;

import java.util.List;
import java.util.UUID;

public interface ICustomer {
    UUID getId();

    String getName();

    String getEmail();

    List<? extends IProduct> getProducts();
}
