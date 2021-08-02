package com.example.demo.DTO;

import lombok.Data;

@Data
public class CarDTO {
    private Integer id;
    private String brand;
    private String model;
    private String description;
    private Integer driverID;
}
