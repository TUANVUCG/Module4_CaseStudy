package com.codegym.repository;

import com.codegym.model.ProductSold;
import com.codegym.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByNameContaining(String category, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    @Query("select p from Product as p group by p.id")
    Page<Product> findAllProduct(Pageable pageable);

    @Query("select p from Product as p WHERE p.category.name = ?1")
    Page<Product> findAllProductByCategory(String category, Pageable pageable);

    @Query(value = "select o.products_id as productid, count(o.products_id) as count, i.quantity as quantity " +
            "from orders_products as o join items i on i.product_id = o.products_id group by o.products_id", nativeQuery = true)
    Iterable<ProductSold> getSold();

    //    Sap xep giam dan
    @Query("select p from Product as p order by p.sellPrice desc ")
    Page<Product> findAllByOrderBySellPriceDesc(Pageable pageable);

    //    Sap xep tang dan theo gia
    @Query("select p from Product as p order by p.sellPrice asc ")
    Page<Product> findAllByOrderBySellPriceAsc(Pageable pageable);
}
