package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double sellPrice;

    private double purchasePrice;

    private double sale;

    private double quantity;

    private double realPrice;

    private double sold;

    @Column(columnDefinition = "text")
    private String img;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    private Category category;

    public double getRealPrice() {
        return sellPrice-sale*1/100*sellPrice;
    }


}
