package com.example.autoservice.dto.mapper;

import com.example.autoservice.dto.master.MasterRequestDto;
import com.example.autoservice.dto.master.MasterResponseDto;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import com.example.autoservice.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class MasterMapper {
    private final OrderService orderService;

    public MasterMapper(OrderService orderService) {
        this.orderService = orderService;
    }

    public MasterResponseDto toDto(Master master) {
        MasterResponseDto dto = new MasterResponseDto();
        dto.setId(master.getId());
        dto.setName(master.getName());
        dto.setFinishedOrdersId(master.getFinishedOrders().stream()
                .map(Order::getId)
                .toList());

        return dto;
    }

    public Master toModel(MasterRequestDto requestDto) {
        Master master = new Master();
        master.setName(requestDto.getName());
        master.setFinishedOrders(requestDto.getFinishedOrdersId().stream()
                .map(orderService::get)
                .toList());

        return master;
    }
}
