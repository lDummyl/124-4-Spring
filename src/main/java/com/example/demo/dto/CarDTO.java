package com.example.demo.dto;

import lombok.Data;

import java.util.List;


@Data
public class CarDTO {

    private Integer id;
    private String model;
    private List<DriverDTO> drivers;

    public void update(CarDTO carDTO){
        this.model = carDTO.getModel();
        this.drivers = carDTO.getDrivers();
    }
}
