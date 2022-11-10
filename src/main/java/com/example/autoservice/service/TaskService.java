package com.example.autoservice.service;

import java.util.List;
import com.example.autoservice.model.Task;

public interface TaskService {
    Task save(Task task);

    Task update(Task task);

    Task updateStatus(Long taskId, Task.PaymentStatus statusPaid);

    Task getById(Long id);

    List<Task> findTasksByMechanicId(Long mechanicId);
}
