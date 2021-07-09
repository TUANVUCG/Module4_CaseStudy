package com.codegym.controller;

import com.codegym.model.Orders;
import com.codegym.service.order.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("add-order")
    public ResponseEntity<?> addItems(Orders order){
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-order/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Orders> itemsOptional = orderService.findById(id);
        if (!itemsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.remove(id);
        return new ResponseEntity<>(itemsOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItems(@PathVariable Long id, @RequestBody Orders order) {
        Optional<Orders> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setId(orderOptional.get().getId());
        return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
    }

}
