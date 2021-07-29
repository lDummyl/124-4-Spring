package com.example.demo.services;

import com.example.demo.entity.Car;
import com.example.demo.model.request.CarRequest;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Car saveCar(CarRequest car) throws RuntimeException;
    String updateCar(CarRequest car);
    Optional<Car> getCar(Long id);
    List<Car> getCars();
    String deleteCar(Long id);
}
