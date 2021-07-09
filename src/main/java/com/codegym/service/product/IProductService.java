package com.codegym.service.product;

import com.codegym.model.ProductSold;
import com.codegym.model.Product;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IProductService extends IGeneralService<Product> {
    Page<Product> findAllByNameContaining(String category, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllProduct(Pageable pageable);

    Page<Product> findAllProductByCategory(String category,Pageable pageable);

    Iterable<ProductSold>getSold();
}
