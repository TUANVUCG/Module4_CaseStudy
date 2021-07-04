package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int point;

    @OneToOne
    private User user;

    @OneToOne
    private Product product;
}
