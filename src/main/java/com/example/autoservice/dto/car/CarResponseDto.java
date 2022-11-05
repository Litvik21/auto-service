package com.example.autoservice.dto.car;

import com.example.autoservice.model.Owner;
import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String brand;
    private String model;
    private String year;
    private String number;
    private Long ownerId;
}
