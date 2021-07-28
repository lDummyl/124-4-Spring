package com.example.demo.services;

import com.example.demo.entities.Car;
import com.example.demo.entities.Driver;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final DriverRepository driverRepository;

    public Car create(String modelName, String carName, String description, Integer driverId) {
        Car car = new Car();
        car.setModelName(modelName);
        car.setCarName(carName);
        car.setDescription(description);
        Driver driver = driverRepository.findById(driverId).orElse(null);
        car.setDriver(driver);
        return repository.save(car);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Car getById(Integer id) {
        Optional<Car> car = repository.findById(id);
        return car.orElse(null);
    }

    public List<Car> getAll() {
        return repository.findAll();
    }

    public Car updateById(Integer id, String modelName, String carName, String description, Integer driverId) {
        Car car = repository.findById(id).orElse(new Car());
        car.setModelName(modelName);
        car.setCarName(carName);
        car.setDescription(description);
        Driver driver = driverRepository.getById(driverId);
        car.setDriver(driver);
        return repository.save(car);
    }
}
