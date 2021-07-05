package com.codegym.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public ModelAndView admin(){
        return new ModelAndView("/admin");
    }

    @GetMapping("/view")
    public ModelAndView view(){
        return new ModelAndView("/view");
    }

}
