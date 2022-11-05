package com.example.autoservice.dto.order;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class OrderRequestDto {
    private Long carId;
    private String description;
    private List<Long> jobIds;
    private List<Long> productsIds;
    private LocalDate dateFinished;
}
