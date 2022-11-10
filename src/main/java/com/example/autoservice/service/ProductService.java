package com.example.autoservice.service;

import com.example.autoservice.model.Product;

public interface ProductService {
    Product save(Product product);

    Product update(Product product);

    Product getById(Long id);
}
