package com.codegym.service.items;

import com.codegym.model.Items;
import com.codegym.repository.IItemsRepository;
import com.codegym.service.items.IItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemsService implements IItemsService {
    @Autowired
    private IItemsRepository itemsRepository;

    @Override
    public Iterable<Items> findAll() {
        return itemsRepository.findAll();
    }

    @Override
    public Optional<Items> findById(Long id) {
        return itemsRepository.findById(id);
    }

    @Override
    public Items save(Items items) {
        return itemsRepository.save(items);
    }

    @Override
    public void remove(Long id) {
        itemsRepository.deleteById(id);
    }

    @Override
    public Page<Items> findAll(Pageable pageable) {
        return itemsRepository.findAll(pageable);
    }

    @Override
    public Iterable<Items> findItemsByCart(Long cartId) {
        return itemsRepository.findItemsByCart(cartId);
    }
}
