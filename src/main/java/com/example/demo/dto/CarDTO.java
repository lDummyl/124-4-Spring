package com.example.demo.dto;

import lombok.Data;

// TODO: 02.08.2021 input DTO and output DTO
@Data
public class CarDTO {
    private Integer id;
    private String modelName;
    private String carName;
    private String description;
    private Integer driverId;
}
