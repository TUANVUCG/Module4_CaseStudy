package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Items;
import com.codegym.model.Product;
import com.codegym.service.items.IItemsService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.product.IProductService;
import com.codegym.service.user.IUserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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


    @GetMapping("/product")
    public ResponseEntity<?> getListProduct() {
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    @GetMapping("xyz")
    public ResponseEntity<?> getListProduc1() {
        return new ResponseEntity<>(productService.getSold(), HttpStatus.OK);
    }

    @GetMapping()
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }
    @GetMapping("/view")
    public ModelAndView showView() {
        ModelAndView modelAndView = new ModelAndView("/view");
        return modelAndView;
    }

    @DeleteMapping ("/delete-items/{id}")
    public ResponseEntity<?> deleteItems(@PathVariable Long id) {
        itemsService.remove(id);
        return new ResponseEntity<>(itemsService.findById(id),HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getListProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Iterable<Items>> getItemsCart() {
        return new ResponseEntity<>(itemsService.findItemsByCart(Long.valueOf(1)), HttpStatus.OK);
    }


//    @GetMapping("/product/{id}")
//    public ModelAndView getProduct(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("/view");
//        modelAndView.addObject("product", productService.findById(id));
//        return modelAndView;
//    }
}
