package com.example.autoservice.controller;

import java.math.BigDecimal;
import java.util.List;
import com.example.autoservice.dto.master.MasterRequestDto;
import com.example.autoservice.dto.master.MasterResponseDto;
import com.example.autoservice.dto.order.OrderResponseDto;
import com.example.autoservice.dto.mapper.MasterMapper;
import com.example.autoservice.dto.mapper.OrderMapper;
import com.example.autoservice.model.Master;
import com.example.autoservice.service.MasterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masters")
public class MasterController {
    private final MasterService masterService;
    private final MasterMapper mapper;
    private final OrderMapper orderMapper;

    public MasterController(MasterService masterService, MasterMapper mapper, OrderMapper orderMapper) {
        this.masterService = masterService;
        this.mapper = mapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @ApiOperation(value = "Save master to DB")
    public MasterResponseDto save(@RequestBody MasterRequestDto requestDto) {
        Master master = mapper.toModel(requestDto);
        return mapper.toDto(masterService.save(master));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update master by id")
    public MasterResponseDto update(
            @PathVariable @ApiParam(value = "id of master that you want to update")
            Long id,
            @RequestBody MasterRequestDto requestDto) {

        Master master = mapper.toModel(requestDto);
        master.setId(id);
        return mapper.toDto(masterService.update(master));
    }

    @GetMapping("/finished-orders/{id}")
    @ApiOperation(value = "Get list orders of master by id")
    public List<OrderResponseDto> getFinishedOrders(
            @PathVariable @ApiParam(value = "id of master that you want to get list of orders")
            Long id) {

        return masterService.getOrders(id).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get salary for master by id")
    public BigDecimal getSalary(
            @PathVariable @ApiParam(value = "id of master that you want to get salary")
            Long id) {

        return masterService.getSalary(id);
    }
}
