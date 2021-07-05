package com.codegym.controller;

import com.codegym.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private ICartService cartServiceCart;
}
