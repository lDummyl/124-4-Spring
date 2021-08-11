package com.example.demo.services;

import com.example.demo.dto.CarDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CarService {

    Set<CarDTO> carSet = new HashSet<>();

    public Set<CarDTO> getAll() {
        return carSet;
    }

    public CarDTO get() {
        return null;
    }

    public void create(CarDTO newCar) {

    }

    public void add(CarDTO newCar) {
    }

    public void update(CarDTO newCar) {
    }

    public void delete(Long id) {
    }
}
