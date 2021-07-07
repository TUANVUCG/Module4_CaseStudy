package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double quantity;

    @OneToOne
    private Product product;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Orders orders;
}
