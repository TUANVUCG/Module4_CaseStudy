package com.codegym.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;

    private String total;

    private int status;

    @ManyToOne
    private User user;

}
