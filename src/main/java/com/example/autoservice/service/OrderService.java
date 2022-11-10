package com.example.autoservice.service;

import java.math.BigDecimal;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;

public interface OrderService {
    Order save(Order order);

    Order update(Order order);

    Order addProduct(Long orderId, Product product);

    Order updateStatus(Long orderId, Order.Status status);

    BigDecimal getPrice(Long id);

    Order getById(Long id);
}