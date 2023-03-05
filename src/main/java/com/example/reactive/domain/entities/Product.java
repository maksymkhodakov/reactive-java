package com.example.reactive.domain.entities;

import com.example.reactive.domain.IProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product implements IProduct {
    @Id
    private String id;
    private String name;
    private int quantity;
    private double price;

    public Product(IProduct product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }
}
