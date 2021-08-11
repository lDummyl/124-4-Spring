package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CarDTO {

    private Long id;
    private String brand;
    private String model;

    //TODO расширить модель для БД many-to-many, one-to-one, one-to-many

    //TODO затолкать в БД
}
