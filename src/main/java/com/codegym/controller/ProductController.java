package com.codegym.controller;


import com.codegym.model.Product;
import com.codegym.service.product.IProductService;
import com.codegym.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ModelAndView getAllProduct(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("product", productService.findAll(pageable));
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editById(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> searchByName(@RequestParam Optional<String> q, @PageableDefault(size = 10) Pageable pageable ){
        if(!q.isPresent()){
            return new ResponseEntity<>(productService.findAll(pageable), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(productService.findAllByNameContaining(q.get(), pageable), HttpStatus.OK);
        }
    }

    @GetMapping("/productList")
    public ResponseEntity<?> findAllProduct(){
        return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
    }
}
