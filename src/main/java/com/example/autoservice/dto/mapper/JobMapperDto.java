package com.example.autoservice.dto.mapper;

import com.example.autoservice.dto.job.JobRequestDto;
import com.example.autoservice.dto.job.JobResponseDto;
import com.example.autoservice.model.Job;
import com.example.autoservice.service.MasterService;
import com.example.autoservice.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class JobMapperDto {
    private final OrderService orderService;
    private final MasterService masterService;

    public JobMapperDto(OrderService orderService, MasterService masterService) {
        this.orderService = orderService;
        this.masterService = masterService;
    }

    public JobResponseDto toDto(Job job) {
        JobResponseDto dto = new JobResponseDto();
        dto.setId(job.getId());
        dto.setOrderId(job.getOrder().getId());
        dto.setMasterId(job.getMaster().getId());
        dto.setPrice(job.getPrice());
        dto.setStatusPaid(job.getStatusPaid());

        return dto;
    }

    public Job toModel(JobRequestDto requestDto) {
        Job job = new Job();
        job.setOrder(orderService.get(requestDto.getOrderId()));
        job.setMaster(masterService.get(requestDto.getMasterId()));
        job.setPrice(requestDto.getPrice());
        job.setStatusPaid(requestDto.getStatusPaid());

        return job;
    }
}
