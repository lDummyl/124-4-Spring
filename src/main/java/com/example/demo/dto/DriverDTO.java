package com.example.demo.dto;

import com.example.demo.entities.Address;
import lombok.Data;

import java.util.Calendar;
import java.util.List;


@Data
public class DriverDTO {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private Calendar birthDate;
    private Address address;
    private List<CarDTO> cars;

    public void update(DriverDTO driver){
        if(driver.getName() != null)
            this.name = driver.getName();
        if(driver.getSurname() != null)
            this.surname = driver.getSurname();
        if(driver.getPhone() != null)
            this.phone = driver.getPhone();
        if(driver.getBirthDate() != null)
            this.birthDate = driver.getBirthDate();
        if(driver.getAddress() != null)
            this.address = driver.getAddress();
        if(driver.getCars() != null)
            this.cars = driver.getCars();
    }
}
