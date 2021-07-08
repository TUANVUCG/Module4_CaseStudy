package com.codegym.service.order;

import com.codegym.model.Orders;
import com.codegym.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Iterable<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }
}
