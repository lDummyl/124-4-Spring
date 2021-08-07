package com.example.demo.dto;

import com.example.demo.entities.Address;
import lombok.Data;


@Data
public class DriverDTO {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private Integer age;
    private Address address;

    public void update(DriverDTO driver){
        if(driver.getName() != null)
            this.name = driver.getName();
        if(driver.getSurname() != null)
            this.surname = driver.getSurname();
        if(driver.getPhone() != null)
            this.phone = driver.getPhone();
        if(driver.getAge() != null)
            this.age = driver.getAge();
        if(driver.getAddress() != null)
            this.address = driver.getAddress();
    }
}
