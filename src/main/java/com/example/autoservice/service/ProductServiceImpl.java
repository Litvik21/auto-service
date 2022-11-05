package com.example.autoservice.service;

import com.example.autoservice.model.Product;
import com.example.autoservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = productRepository.getReferenceById(product.getId());
        return productRepository.save(productToUpdate);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getReferenceById(id);
    }
}
