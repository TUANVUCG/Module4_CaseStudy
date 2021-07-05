package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class CartController {
    @Autowired
    private ICartService cartServiceCart;


}
