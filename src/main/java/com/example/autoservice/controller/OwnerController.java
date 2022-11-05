package com.example.autoservice.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.example.autoservice.dto.order.OrderResponseDto;
import com.example.autoservice.dto.owner.OwnerRequestDto;
import com.example.autoservice.dto.owner.OwnerResponseDto;
import com.example.autoservice.dto.mapper.OrderMapper;
import com.example.autoservice.dto.mapper.OwnerMapper;
import com.example.autoservice.model.Owner;
import com.example.autoservice.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final OwnerMapper mapper;
    private final OrderMapper orderMapper;

    public OwnerController(OwnerService ownerService, OwnerMapper mapper,
                           OrderMapper orderMapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @ApiOperation(value = "Save owner to DB")
    public OwnerResponseDto save(@RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerService.save(mapper.toModel(requestDto));
        return mapper.toDto(owner);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update owner by id")
    public OwnerResponseDto update(
            @PathVariable @ApiParam(value = "id of owner that you want to update")
            Long id,
            @RequestBody OwnerRequestDto requestDto) {

        Owner owner = mapper.toModel(requestDto);
        owner.setId(id);
        return mapper.toDto(ownerService.update(owner));
    }

    @GetMapping("/orders/{id}")
    @ApiOperation(value = "Get all owner orders by id")
    public List<OrderResponseDto> getAllOrders(
            @PathVariable @ApiParam(value = "id of owner that you want to get all orders")
            Long id) {

        return ownerService.findAllOrdersById(id).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
