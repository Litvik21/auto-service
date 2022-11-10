package com.example.autoservice.service;

import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;
import com.example.autoservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    private Order order;
    private Product product;

    private List<Product> products;

    @BeforeEach
    void setUp() {
        product = new Product(1L, "title", BigDecimal.valueOf(660));
        Product product1 = new Product(1L, "title", BigDecimal.valueOf(300));
        Product product2 = new Product(1L, "title", BigDecimal.valueOf(134));
        Product product3 = new Product(1L, "title", BigDecimal.valueOf(443));
        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        order = new Order(1L, null, "description", LocalDate.now(),
                Collections.emptyList(),
                products,
                Order.Status.PROCESSING,
                BigDecimal.valueOf(887),
                LocalDate.now());
    }

    @Test
    void shouldReturnUpdatedTotalPrice() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.ofNullable(order));
        //Mockito.when(orderService.getById(1L)).thenReturn(order);
        Assertions.assertEquals(BigDecimal.valueOf(887), order.getTotalPrice());
        Order actual = orderService.addProduct(1L, product);
        Assertions.assertEquals(4, actual.getProducts().size());
        Assertions.assertEquals(BigDecimal.valueOf(1475.52), actual.getTotalPrice());
    }
}