package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double salePrice;

    private double purchasePrice;

    private double quantity;

    @Column(columnDefinition = "text")
    private String img;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    private Category category;
}
