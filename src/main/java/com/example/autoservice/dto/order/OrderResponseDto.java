package com.example.autoservice.dto.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import com.example.autoservice.model.Status;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String description;
    private LocalDate dateReceived;
    private List<Long> jobIds;
    private List<Long> productsIds;
    private Status status;
    private BigDecimal totalPrice;
    private LocalDate dateFinished;
}
