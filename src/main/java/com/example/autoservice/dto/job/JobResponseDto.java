package com.example.autoservice.dto.job;

import java.math.BigDecimal;
import com.example.autoservice.model.StatusPaid;
import lombok.Data;

@Data
public class JobResponseDto {
    private Long id;
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private StatusPaid statusPaid;
}
