package com.example.demo.services;

import com.example.demo.dto.CarDTO;
import com.example.demo.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Validated
@Service
public class CarService {

    Map<Long,CarDTO> carMap = new HashMap<>();

    public Set<CarDTO> getAll() {
        return new HashSet<>(carMap.values());
    }

    public CarDTO getById(Long id) {
        CarDTO car = carMap.get(id);
        if (car == null) {
            throw new NotFoundException(id);
        }
        return car;
    }

    public CarDTO add(CarDTO newCar) {
        Long id = 1L + carMap.keySet().stream().max(Long::compare).orElse(0L);
        newCar.setId(id);
        carMap.put(newCar.getId(),newCar);
        return newCar;
    }

    public CarDTO update(CarDTO newCar) {
        Long id = newCar.getId();
        CarDTO oldCar = carMap.get(id);
        if (oldCar == null) {
            throw new NotFoundException(id);
        }
        newCar.setBrand((newCar.getBrand() == null) ? oldCar.getBrand() : newCar.getBrand());
        newCar.setModel((newCar.getModel() == null) ? oldCar.getModel() : newCar.getModel());

        carMap.put(newCar.getId(),newCar);

        return newCar;
    }

    public void delete(Long id) {
        CarDTO car = carMap.get(id);
        if (car == null) {
            throw new NotFoundException(id);
        }
        carMap.remove(id);
    }
}
