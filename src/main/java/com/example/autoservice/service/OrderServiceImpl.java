package com.example.autoservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.example.autoservice.model.*;
import com.example.autoservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        Order orderToUpdate = orderRepository.getReferenceById(order.getId());
        return orderRepository.save(orderToUpdate);
    }

    @Override
    public Order addProduct(Long orderId, Product product) {
        Order order = orderRepository.getReferenceById(orderId);
        List<Product> products = order.getProducts();
        products.add(product);
        order.setProducts(products);
        updateTotalPrice(order);
        return order;
    }

    @Override
    public Order updateStatus(Long orderId, Status status) {
        Order order = orderRepository.getReferenceById(orderId);
        order.setStatus(status);
        checkStatus(order);
        return orderRepository.save(order);
    }

    @Override
    public Order get(Long id) {
        return orderRepository.getReferenceById(id);
    }

    @Override
    public BigDecimal getPrice(Long id) {
        Order order = orderRepository.getReferenceById(id);
        return order.getTotalPrice();
    }

    private void checkStatus(Order order) {
        if (order.getStatus() == Status.SUCCESSFULLY_COMPLETED
                || order.getStatus() == Status.NOT_SUCCESSFULLY_COMPLETED) {
            order.setDateFinished(LocalDate.now());
        }
    }

    private void updateTotalPrice(Order order) {
        double totalPriceProducts = order.getProducts().stream()
                .map(Product::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        double totalPriceJobs = order.getJobs().stream()
                .map(Job::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        int countOfProducts = order.getProducts().size();
        int countOfJobs = order.getJobs().size();
        double totalPriceWithOutSale = totalPriceJobs + totalPriceProducts;
        double sale = (totalPriceJobs + totalPriceProducts) * (countOfProducts + countOfJobs * 2);
        order.setTotalPrice(BigDecimal.valueOf(totalPriceWithOutSale - sale));
        orderRepository.save(order);
    }
}
