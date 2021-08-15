package com.example.demo.dto;

import com.example.demo.entities.Address;
import lombok.Data;

import java.util.List;

@Data
public class OutDriverDTO {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String birthDate;
    private Address address;
    private List<CarDTO> cars;
}
