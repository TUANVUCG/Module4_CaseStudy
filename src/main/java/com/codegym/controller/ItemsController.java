package com.codegym.controller;


import com.codegym.model.Items;
import com.codegym.service.items.IItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping()
public class ItemsController {
    @Autowired
    private IItemsService itemsService;

    @PostMapping("/add-items")
    public ResponseEntity<?> addItems(Items items){
        return new ResponseEntity<>(itemsService.save(items), HttpStatus.CREATED);
    }

    @DeleteMapping ("/remove-items/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Items> itemsOptional = itemsService.findById(id);
        if (!itemsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemsService.remove(id);
        return new ResponseEntity<>(itemsOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit-order/{id}")
    public ResponseEntity<Items> updateItems(@PathVariable Long id, @RequestBody Items items) {
        Optional<Items> itemsOptional = itemsService.findById(id);
        if (!itemsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        items.setId(itemsOptional.get().getId());
        return new ResponseEntity<>(itemsService.save(items), HttpStatus.OK);
    }
}
