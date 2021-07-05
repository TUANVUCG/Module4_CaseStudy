package com.codegym.controller;

import com.codegym.service.items.IItemsService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/shopee")
public class IndexController {
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private IItemsService itemsService;
//
//    @Autowired
//
//    private IOrderService orderService;
//    @GetMapping("/")
//    public ModelAndView index() {
//        return new ModelAndView("/index");
//    }
}
