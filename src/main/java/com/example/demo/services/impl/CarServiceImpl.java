package com.example.demo.services.impl;

import com.example.demo.entity.Car;
import com.example.demo.entity.Driver;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.request.CarRequest;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.DriverRepository;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    @Override
    public Car saveCar(CarRequest carRequest) throws RuntimeException {
        Optional<Driver> driver = driverRepository.findById(carRequest.getDriverId());
        if (driver.isPresent()) {
            Car car = new Car();
            mapperUpdateCarToCar(carRequest, car, driver.get());
            return carRepository.save(car);
        } else throw new BusinessException("Driver id " + carRequest.getDriverId() + " not found!");
    }

    @Override
    public String updateCar(CarRequest carRequest) {
        String response;
        if (carRequest.getId() != null) {
            Optional<Car> carOptional = carRepository.findById(carRequest.getId());
            if (carOptional.isPresent()) {

                if (carRequest.getDriverId() != null) {
                    Optional<Driver> driver = driverRepository.findById(carRequest.getDriverId());
                    if (driver.isPresent()) {
                        carRepository.save(mapperUpdateCarToCar(carRequest, carOptional.get(), driver.get()));
                        log.info("Update car, id: {}", carRequest.getId());
                        response = "Car updated successfully!";
                    } else throw new BusinessException("Driver not found!");
                } else {
                    carRepository.save(mapperUpdateCarToCarNotDriver(carRequest, carOptional.get()));
                    log.info("Update car, id: {}", carRequest.getId());
                    response = "Car updated successfully!";
                }

            } else throw new BusinessException("Car not found!");

        } else throw new BusinessException("Car id cannot be null");

        return response;
    }

    @Override
    public Optional<Car> getCar(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public String deleteCar(Long id) {
        String response;
        Optional<Car> byId = carRepository.findById(id);
        if (byId.isPresent()) {
            carRepository.delete(byId.get());
            log.info("Car {} was deleted!", id);
            response = "Car " + id + " was deleted!";
        } else throw new BusinessException("Car not found");

        return response;
    }

    private Car mapperUpdateCarToCar(CarRequest update, Car car, Driver driver) {
        car.setModel(update.getModel());
        car.setBrand(update.getBrand());
        car.setCategory(update.getCategory());
        car.setDriver(driver);
        return car;
    }

    private Car mapperUpdateCarToCarNotDriver(CarRequest update, Car car) {
        car.setModel(update.getModel());
        car.setBrand(update.getBrand());
        car.setCategory(update.getCategory());
        return car;
    }
}
