package com.example.autoservice.service;

import java.util.List;
import com.example.autoservice.model.Job;
import com.example.autoservice.model.StatusPaid;

public interface JobService {
    Job save(Job job);

    Job update(Job job);

    Job updateStatus(Long jobId, StatusPaid statusPaid);

    Job get(Long id);

    List<Job> findJobsByMasterId(Long masterId);
}
