package com.example.demo.services.impl;

import com.example.demo.entity.Driver;
import com.example.demo.model.request.DriverUpdate;
import com.example.demo.repository.DriverRepository;
import com.example.demo.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public Driver saveDriver(Driver user) {
        return driverRepository.save(user);
    }

    @Override
    public String updateDriver(DriverUpdate update) {
        String response;
        Optional<Driver> driverOptional = driverRepository.findById(update.getId());
        if(driverOptional.isPresent()) {
            driverRepository.save(mapperUpdateToDriver(update, driverOptional.get()));
            log.info("Update driver, id: {}", update.getId());
            response = "Driver updated successfully!";
        } else {
            response = "Driver not found!";
        }

        return response;
    }

    @Override
    public Optional<Driver> getDriver(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public String deleteDriver(Long id) {
        String response;
        Optional<Driver> byId = driverRepository.findById(id);
        if(byId.isPresent()) {
            driverRepository.delete(byId.get());
            log.info("Driver {} was deleted!", id);
            response = "Driver " + id + "  was deleted!";
        } else response = "Driver not found";

        return response;
    }

    private Driver mapperUpdateToDriver(DriverUpdate update, Driver driver) {
        driver.setFirstName(update.getFirstName());
        driver.setLastName(update.getLastName());
        driver.setDriverLicense(update.getDriverLicense());
        return driver;
    }
}
