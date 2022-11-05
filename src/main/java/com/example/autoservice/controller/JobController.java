package com.example.autoservice.controller;

import com.example.autoservice.dto.job.JobRequestDto;
import com.example.autoservice.dto.job.JobResponseDto;
import com.example.autoservice.dto.mapper.JobMapperDto;
import com.example.autoservice.model.Job;
import com.example.autoservice.model.StatusPaid;
import com.example.autoservice.service.JobService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobMapperDto mapper;
    private final JobService jobService;

    public JobController(JobMapperDto mapper, JobService jobService) {
        this.mapper = mapper;
        this.jobService = jobService;
    }

    @PostMapping
    @ApiOperation(value = "Save a new job to DB")
    public JobResponseDto save(@RequestBody JobRequestDto requestDto) {
        Job job = mapper.toModel(requestDto);
        return mapper.toDto(jobService.save(job));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update job by id")
    public JobResponseDto update(
            @PathVariable @ApiParam(value = "id of job that you want to update")
            Long id,
            @RequestBody JobRequestDto requestDto) {

        Job job = mapper.toModel(requestDto);
        job.setId(id);
        return mapper.toDto(jobService.update(job));
    }

    @PutMapping("/update-status/{id}")
    @ApiOperation(value = "Update status of job")
    public JobResponseDto updateStatus(
            @PathVariable @ApiParam(value = "id of job that you want to update status")
            Long id,
            @RequestBody @ApiParam(value = "Updated status for job")
            String status) {

        return mapper.toDto(jobService.updateStatus(id,
                StatusPaid.valueOf(status)));
    }
}
