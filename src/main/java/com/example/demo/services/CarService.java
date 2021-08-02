package com.example.demo.services;

import com.example.demo.dto.CarDTO;
import com.example.demo.entities.Car;
import com.example.demo.entities.Driver;
import com.example.demo.exceptions.CarDriverApiException;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final DriverRepository driverRepository;

    private static final String NO_CAR_MESSAGE = "There is no such car!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public Car create(String modelName, String carName, String description, Integer driverId) {
        Car car = new Car();
        car.setModelName(modelName);
        car.setCarName(carName);
        car.setDescription(description);
        Driver driver = driverRepository.findById(driverId).orElse(null);
        car.setDriver(driver);
        return repository.save(car);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new CarDriverApiException(NO_CAR_MESSAGE);
        }
        repository.deleteById(id);
    }

    public Car getById(Integer id) {
        Optional<Car> car = repository.findById(id);
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new CarDriverApiException(NO_CAR_MESSAGE);
        }
    }

    public List<Car> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Car update(Integer id, String modelName, String carName, String description, Integer driverId) {
        Car car = repository.findById(id).orElseThrow(() -> new CarDriverApiException(NO_CAR_MESSAGE));
        car.setModelName(modelName);
        car.setCarName(carName);
        car.setDescription(description);
        Driver driver = driverRepository.findById(driverId).orElse(null);
        car.setDriver(driver);
        return repository.save(car);
    }

    public CarDTO toDTO(Car car) {
        if (car == null) {
            throw new CarDriverApiException(DTO_MUST_NOT_BE_NULL_MESSAGE);
        }
        CarDTO dto = new CarDTO();
        dto.setCarName(car.getCarName());
        dto.setModelName(car.getModelName());
        dto.setDescription(car.getDescription());
        dto.setDriverId(Optional.ofNullable(car.getDriver()).map(Driver::getId).orElse(null));
        dto.setId(car.getId());
        return dto;
    }
}