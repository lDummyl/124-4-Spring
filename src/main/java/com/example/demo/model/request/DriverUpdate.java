package com.example.demo.model.request;

import lombok.Data;

@Data
public class DriverUpdate {
    private Long id;
    private String firstName;
    private String lastName;
    private String driverLicense;
}
