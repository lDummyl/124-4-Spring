package com.example.demo.dto;

import lombok.Data;

import java.util.List;


@Data
public class CarDTO {

    private Integer id;
    private String model;
    private List<GarageDTO> garages;

    public void update(CarDTO carDTO){
        if(carDTO.getModel() != null)
            this.model = carDTO.getModel();
        if(carDTO.getGarages() != null)
            this.garages = carDTO.getGarages();
    }
}
