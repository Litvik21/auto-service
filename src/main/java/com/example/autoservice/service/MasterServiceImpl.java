package com.example.autoservice.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.autoservice.model.Job;
import com.example.autoservice.model.Master;
import com.example.autoservice.model.Order;
import com.example.autoservice.model.StatusPaid;
import com.example.autoservice.repository.MasterRepository;
import org.springframework.stereotype.Service;

@Service
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;
    private final JobService jobService;

    public MasterServiceImpl(MasterRepository masterRepository, JobService jobService) {
        this.masterRepository = masterRepository;
        this.jobService = jobService;
    }

    @Override
    public Master save(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master update(Master master) {
        Master masterToUpdate = masterRepository.getReferenceById(master.getId());
        return masterRepository.save(masterToUpdate);
    }

    @Override
    public List<Order> getOrders(Long masterId) {
        Master master = masterRepository.getReferenceById(masterId);
        return master.getFinishedOrders();
    }

    @Override
    public BigDecimal getSalary(Long masterId) {
        List<Job> jobsByMasterId = jobService.findJobsByMasterId(masterId);
        double totalPriceForJob = jobsByMasterId.stream()
                .map(Job::getPrice)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        double masterSalary = totalPriceForJob * 0.4;

        updateStatusOfJobMaster(jobsByMasterId);
        return BigDecimal.valueOf(masterSalary);
    }

    @Override
    public Master get(Long id) {
        return masterRepository.getReferenceById(id);
    }

    private void updateStatusOfJobMaster(List<Job> jobs) {
        for (Job job : jobs) {
            jobService.updateStatus(job.getId(), StatusPaid.PAID);
        }
    }
}
