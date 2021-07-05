package com.codegym.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class UserController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        System.out.printf("Model ----> "+modelAndView);
        return modelAndView;
    }
}
