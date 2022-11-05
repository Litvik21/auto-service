package com.example.autoservice.dto.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;
import com.example.autoservice.dto.order.OrderRequestDto;
import com.example.autoservice.dto.order.OrderResponseDto;
import com.example.autoservice.model.Job;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Product;
import com.example.autoservice.model.Status;
import com.example.autoservice.service.CarService;
import com.example.autoservice.service.JobService;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final OrderService orderService;
    private final CarService carService;
    private final ProductService productService;
    private final JobService jobService;

    public OrderMapper(OrderService orderService, CarService carService,
                       ProductService productService, JobService jobService) {
        this.orderService = orderService;
        this.carService = carService;
        this.productService = productService;
        this.jobService = jobService;
    }

    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setCarId(order.getCar().getId());
        dto.setDescription(order.getDescription());
        dto.setDateReceived(order.getDateReceived());
        dto.setJobIds(order.getJobs().stream()
                .map(Job::getId)
                .collect(Collectors.toList()));
        dto.setProductsIds(order.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        dto.setStatus(order.getStatus());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setDateFinished(order.getDateFinished());

        return dto;
    }

    public Order toModel(OrderRequestDto requestDto) {
        Order order = new Order();
        order.setCar(carService.get(requestDto.getCarId()));
        order.setDescription(requestDto.getDescription());
        order.setDateReceived(LocalDate.now());
        order.setJobs(requestDto.getJobIds().stream()
                .map(jobService::get)
                .toList());
        order.setProducts(requestDto.getProductsIds().stream()
                .map(productService::get)
                .toList());
        order.setStatus(Status.RECEIVED);
        order.setTotalPrice(BigDecimal.valueOf(0));
        order.setDateFinished(requestDto.getDateFinished());

        return order;
    }
}
