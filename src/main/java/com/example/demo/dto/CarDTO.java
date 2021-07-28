package com.example.demo.dto;

import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String modelName;
    private String carName;
    private String description;
    private Long driverId;
}
