package com.example.autoservice.service;

import java.math.BigDecimal;
import java.util.List;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;
import com.example.autoservice.model.Status;

public interface OrderService {
    Order save(Order order);

    Order update(Order order);

    Order addProduct(Long orderId, Product product);

    Order updateStatus(Long orderId, Status status);

    BigDecimal getPrice(Long id);

    Order get(Long id);
}