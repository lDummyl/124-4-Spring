package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Data
public class DriverDTO {

    private Long id;
    private String name;
    private String secondName;
    private String lastName;

    private Set<CarDTO> cars;

    //TODO затолкать в БД
}
