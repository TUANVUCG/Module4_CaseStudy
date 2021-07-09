package com.codegym.service.product;

import com.codegym.model.ProductSold;
import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
    productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByNameContaining(String category , Pageable pageable) {
        return productRepository.findAllByNameContaining(category, pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        for (Product product : productRepository.findAllProduct(pageable)){
            for (ProductSold demo : getSold()){
                if (product.getId() == demo.getProductId()){
                    product.setSold(demo.getCount()+demo.getQuantity());
                    productRepository.save(product);
                }
            }
        }
        return productRepository.findAllProduct(pageable);
    }

    @Override
    public Iterable<ProductSold> getSold() {
        return productRepository.getSold();
    }
}
