package com.example.demo.DTO;

import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private String description;
    private Long driverID;
}
