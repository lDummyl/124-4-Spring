package com.example.demo.dto;

import lombok.Data;

@Data
public class DriverDTO {
    private Integer id;
    private String name;
    private String surname;
    private String phone;

    public void update(DriverDTO driver){
        this.name = driver.getName();
        this.surname = driver.getSurname();
        this.phone = driver.getPhone();
    }
}
