package com.example.demo.dto.out;

import lombok.Data;

@Data
public class CarOut {
    private Integer id;
    private String modelName;
    private String carName;
    private String description;
    private Integer driverId;
}
