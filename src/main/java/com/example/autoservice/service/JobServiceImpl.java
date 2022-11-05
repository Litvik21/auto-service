package com.example.autoservice.service;

import com.example.autoservice.model.Job;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.StatusPaid;
import com.example.autoservice.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job update(Job job) {
        Job jobToUpdate = jobRepository.getReferenceById(job.getId());
        return jobRepository.save(jobToUpdate);
    }

    @Override
    public Job updateStatus(Long jobId, StatusPaid statusPaid) {
        Job jobToUpdate = jobRepository.getReferenceById(jobRepository.count());
        jobToUpdate.setStatusPaid(statusPaid);
        return jobRepository.save(jobToUpdate);
    }

    @Override
    public List<Job> findJobsByMasterId(Long masterId) {
        return jobRepository.findJobsByMasterId(masterId);
    }

    @Override
    public Job get(Long id) {
        return jobRepository.getReferenceById(id);
    }
}
