package com.example.demo.dto;

import com.example.demo.entities.Address;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class GarageDTO {
    private Integer id;
    private Address address;
    private List<Integer> carId = new LinkedList<>();
}
