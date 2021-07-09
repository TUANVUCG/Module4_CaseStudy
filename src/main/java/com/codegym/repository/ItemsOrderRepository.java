package com.codegym.repository;

import com.codegym.model.ItemsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsOrderRepository extends JpaRepository<ItemsOrder, Long> {
}
