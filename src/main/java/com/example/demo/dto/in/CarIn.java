package com.example.demo.dto.in;

import lombok.Data;

@Data
public class CarIn {
    private Integer id;
    private String modelName;
    private String carName;
    private String description;
    private Integer driverId;
}
