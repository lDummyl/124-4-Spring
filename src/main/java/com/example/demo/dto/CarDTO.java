package com.example.demo.dto;

import lombok.Data;

@Data
public class CarDTO {
    private Integer id;
    private String modelName;
    private String carName;
    private String description;
    private Integer driverId;
}
