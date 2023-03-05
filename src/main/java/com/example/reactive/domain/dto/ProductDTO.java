package com.example.reactive.domain.dto;

import com.example.reactive.domain.IProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements IProduct {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public ProductDTO(IProduct product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }
}
