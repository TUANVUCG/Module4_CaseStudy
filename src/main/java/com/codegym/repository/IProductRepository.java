package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByNameContaining(String category , Pageable pageable);

    @Query("select p from Product as p group by p.id")
    Page<Product> findAll(Pageable pageable);

    @Query("select p from Product as p group by p.id")
    Iterable<Product> findAllProduct();
}
