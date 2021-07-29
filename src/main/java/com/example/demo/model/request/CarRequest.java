package com.example.demo.model.request;

import lombok.Data;

@Data
public class CarRequest {
    private Long id;
    private String brand;
    private String model;
    private Character category;
    private Long driverId;
}
