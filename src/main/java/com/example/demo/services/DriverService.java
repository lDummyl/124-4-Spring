package com.example.demo.services;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.DriverDTO;
import com.example.demo.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Validated
@Service
public class DriverService {
    Map<Long, DriverDTO> driverMap = new HashMap<>();

    public Set<DriverDTO> getAll() {
        return new HashSet<>(driverMap.values());
    }

    public DriverDTO getById(Long id) {
        DriverDTO driver = driverMap.get(id);
        if (driver == null) {
            throw new NotFoundException(id);
        }
        return driver;
    }

    public DriverDTO add(DriverDTO newDriver) {
        Long id = 1L + driverMap.keySet().stream().max(Long::compare).orElse(0L);
        newDriver.setId(id);
        driverMap.put(newDriver.getId(),newDriver);
        return newDriver;
    }

    public DriverDTO update(DriverDTO newDriver) {
        Long id = newDriver.getId();
        DriverDTO oldDriver = driverMap.get(id);
        if (oldDriver == null) {
            throw new NotFoundException(id);
        }
        newDriver.setName((newDriver.getName() == null) ? oldDriver.getName() : newDriver.getName());
        newDriver.setSecondName((newDriver.getSecondName() == null) ? oldDriver.getSecondName() : newDriver.getSecondName());
        newDriver.setLastName((newDriver.getLastName() == null) ? oldDriver.getLastName() : newDriver.getLastName());
        newDriver.setCars((newDriver.getCars() == null) ? oldDriver.getCars() : newDriver.getCars());

        driverMap.put(newDriver.getId(),newDriver);

        return newDriver;
    }

    public void delete(Long id) {
        DriverDTO driver = driverMap.get(id);
        if (driver == null) {
            throw new NotFoundException(id);
        }
        driverMap.remove(id);
    }
}
