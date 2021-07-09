package com.codegym.controller;

import com.codegym.model.Cart;
import com.codegym.model.Items;
import com.codegym.model.Product;
import com.codegym.repository.ICartRepository;
import com.codegym.service.cart.ICartService;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.items.IItemsService;
import com.codegym.service.order.IOrderService;
import com.codegym.service.product.IProductService;
import com.codegym.service.user.IUserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @Autowired
    private ICategoryService categoryService;


    @GetMapping("/product")
    public ResponseEntity<?> getListProduct(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(productService.findAllProduct(pageable), HttpStatus.OK);
    }
    @GetMapping("/product/page/{page}")
    public ResponseEntity<?> changePage(@PageableDefault(size = 10) Pageable pageable,@PathVariable int page) {
        return new ResponseEntity<>(productService.findAllProduct(pageable.withPage(page)), HttpStatus.OK);
    }

    @GetMapping()
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }
    @GetMapping("/view/**")
    public ModelAndView showView() {
        ModelAndView modelAndView = new ModelAndView("/view");
        return modelAndView;
    }
    @PostMapping ("/view/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }

    @PostMapping ("/add-items")
    public ResponseEntity<Items> getProduct(@RequestBody Items items) {
        return new ResponseEntity<>(itemsService.save(items),HttpStatus.CREATED);
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

    @GetMapping("/order")
    public ModelAndView showOrder() {
        ModelAndView modelAndView = new ModelAndView("/cart");
        return modelAndView;
    }
    @GetMapping("/cart")
    public ResponseEntity<?> getItemsCart() {
        return new ResponseEntity<>(itemsService.findItemsByCart(Long.valueOf(1)), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getListCategory() {
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }
}
