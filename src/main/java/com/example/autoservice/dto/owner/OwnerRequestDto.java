package com.example.autoservice.dto.owner;

import java.util.List;
import lombok.Data;

@Data
public class OwnerRequestDto {
    private List<Long> carsId;
    private List<Long> ordersId;
}
