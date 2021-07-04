package com.codegym.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Column(unique = true)
    private String account;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "text")
    private String avatar;

    @Column(columnDefinition = "text")
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

}
