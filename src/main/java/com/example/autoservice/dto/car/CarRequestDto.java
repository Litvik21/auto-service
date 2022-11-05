package com.example.autoservice.dto.car;

import lombok.Data;

@Data
public class CarRequestDto {
    private String brand;
    private String model;
    private String year;
    private String number;
    private Long ownerId;
}
