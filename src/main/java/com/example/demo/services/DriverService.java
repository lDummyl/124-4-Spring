package com.example.demo.services;

import com.example.demo.entity.Driver;
import com.example.demo.model.request.DriverUpdate;

import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver saveDriver(Driver driver);
    String updateDriver(DriverUpdate driver);
    Optional<Driver> getDriver(Long id);
    List<Driver> getDrivers();
    String deleteDriver(Long id);
}
