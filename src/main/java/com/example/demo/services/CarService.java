package com.example.demo.services;

import com.example.demo.entities.Car;
import com.example.demo.entities.Driver;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final DriverRepository driverRepository;

    public Car create(String modelName, String carName, String description, Long driverId) {
        Car car = new Car();
        car.setModelName(modelName);
        car.setCarName(carName);
        car.setDescription(description);
        Driver driver = driverRepository.getById(driverId);
        car.setDriver(driver);
        return repository.save(car);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Car getById(Long id) {
        return repository.getById(id);
    }

    public List<Car> getAll() {
        return repository.findAll();
    }

    public Car updateById(Long id, String modelName, String carName, String description, Long driverId) {
        Car car = repository.getById(id);
        car.setModelName(modelName);
        car.setCarName(carName);
        car.setDescription(description);
        Driver driver = driverRepository.getById(driverId);
        car.setDriver(driver);
        return repository.save(car);
    }
}
