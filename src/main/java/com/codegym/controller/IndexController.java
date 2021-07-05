package com.codegym.controller;

import com.codegym.service.items.IItemsService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.product.IProductService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/shopee")
public class IndexController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IItemsService itemsService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;


    @GetMapping()
    public ResponseEntity<?> index() {
        return new ResponseEntity<>(productService.findAll(Pageable.ofSize(5)), HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
    }

//    @GetMapping("")
//    public ModelAndView getAllSmartphonePage(@PageableDefault(size = 24) Pageable pageable) {
//        ModelAndView modelAndView = new ModelAndView("/index");
//        modelAndView.addObject("smartphones", productService.findAll(pageable));
//        return modelAndView;
//    }
}
