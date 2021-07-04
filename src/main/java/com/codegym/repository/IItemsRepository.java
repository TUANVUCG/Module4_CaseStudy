package com.codegym.repository;

import com.codegym.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemsRepository extends JpaRepository<Items, Long> {
}
