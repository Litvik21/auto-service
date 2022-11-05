package com.example.autoservice.dto.owner;

import java.util.List;
import lombok.Data;

@Data
public class OwnerResponseDto {
    private Long id;
    private List<Long> carsId;
    private List<Long> ordersId;
}
