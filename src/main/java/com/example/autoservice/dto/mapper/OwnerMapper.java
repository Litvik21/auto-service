package com.example.autoservice.dto.mapper;

import java.util.stream.Collectors;
import com.example.autoservice.dto.owner.OwnerRequestDto;
import com.example.autoservice.dto.owner.OwnerResponseDto;
import com.example.autoservice.model.Car;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.Owner;
import com.example.autoservice.service.CarService;
import com.example.autoservice.service.OrderService;
import com.example.autoservice.service.OwnerService;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    private final OwnerService ownerService;
    private final CarService carService;

    private final OrderService orderService;

    public OwnerMapper(OwnerService ownerService, CarService carService,
                       OrderService orderService) {
        this.ownerService = ownerService;
        this.carService = carService;
        this.orderService = orderService;
    }

    public OwnerResponseDto toDto(Owner owner) {
        OwnerResponseDto dto = new OwnerResponseDto();
        dto.setId(owner.getId());
        dto.setOrdersId(owner.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        dto.setCarsId(owner.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));

        return dto;
    }

    public Owner toModel(OwnerRequestDto requestDto) {
        Owner owner = new Owner();
        owner.setCars(requestDto.getCarsId().stream()
                .map(carService::get)
                .toList());
        owner.setOrders(requestDto.getOrdersId().stream()
                .map(orderService::get)
                .toList());

        return owner;
    }
}
