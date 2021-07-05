package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;


public interface IProductService extends IGeneralService<Product> {
    Page<Product> findAllByCategoryContaining(String category);
}
