package com.example.autoservice.repository;

import java.util.List;
import com.example.autoservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByMechanicId(Long mechanicId);
}
