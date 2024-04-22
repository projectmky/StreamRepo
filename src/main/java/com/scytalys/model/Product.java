package com.scytalys.model;


import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String name;
    private double price;
    private int quantity;
    private String description;
//    @ToString.Exclude
//    private ProductSupplier productSupplier;
}
