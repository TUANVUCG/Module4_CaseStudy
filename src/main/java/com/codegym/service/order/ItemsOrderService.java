package com.codegym.service.order;

import com.codegym.model.ItemsOrder;
import com.codegym.repository.IItemsRepository;
import com.codegym.repository.ItemsOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ItemsOrderService implements IItemOrderService {

    @Autowired
    private ItemsOrderRepository itemsOrderRepository;
    @Override
    public Iterable<ItemsOrder> findAll() {
        return itemsOrderRepository.findAll();
    }

    @Override
    public Optional<ItemsOrder> findById(Long id) {
        return itemsOrderRepository.findById(id);
    }

    @Override
    public ItemsOrder save(ItemsOrder itemsOrder) {
        return itemsOrderRepository.save(itemsOrder);
    }

    @Override
    public void remove(Long id) {
        itemsOrderRepository.deleteById(id);
    }
}
