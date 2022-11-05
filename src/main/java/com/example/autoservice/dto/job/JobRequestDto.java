package com.example.autoservice.dto.job;

import java.math.BigDecimal;
import com.example.autoservice.model.StatusPaid;
import lombok.Data;

@Data
public class JobRequestDto {
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private StatusPaid statusPaid;
}
