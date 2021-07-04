package com.codegym.repository;

import com.codegym.model.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IItemsRepository extends JpaRepository<Items, Long> {
    @Query("select i from Items as i where i.id = ?1")
    Page<Items> findItemsByCart(Long cartId, Pageable pageable);
}
