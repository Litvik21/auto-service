package com.example.autoservice.repository;

import java.util.List;
import com.example.autoservice.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findJobsByMasterId(Long masterId);
}
