package com.example.demo.services.crud;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.DriverDTO;
import com.example.demo.entity.Car;
import com.example.demo.entity.Driver;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.DriverRepository;
import com.example.demo.services.crud.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chervinko <br>
 * 28.07.2021
 */
@Service
@RequiredArgsConstructor
public class CarDbService implements CarService {
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    @Override
    public CarDTO.Response.Public get(Long id) {
        final Car car = carRepository.findById(id).orElse(null);
        if (car == null) return null;

        return toResponsePublicDTO(car);
    }

    @Override
    public List<CarDTO.Response.Public> getAll() {
        List<CarDTO.Response.Public> dtos = new ArrayList<>();
        carRepository.findAll().forEach(car -> dtos.add(toResponsePublicDTO(car)));

        return dtos;
    }

    @Override
    public CarDTO.Response.Public create(CarDTO.Request.Create dto) {
        Car car = new Car();
        car.setMake(dto.getMake());
        car.setModel(dto.getModel());
        car.setRegistrationNumber(dto.getRegistrationNumber());

        Long driverId = dto.getDriverId();
        if (dto.getDriverId() != null) {
            final Driver driver = driverRepository.findById(driverId).orElse(null);
            if (driver == null) throw new EntityNotFoundException(Driver.class, driverId);
            car.setDriver(driver);
        }

        return toResponsePublicDTO(carRepository.save(car));
    }

    @Override
    public CarDTO.Response.Public update(CarDTO.Request.Update dto) {
        final Car car = carRepository.findById(dto.getId()).orElse(null);
        if (car == null) throw new EntityNotFoundException(Car.class, dto.getId());

        if (dto.getMake() != null) car.setMake(dto.getMake());
        if (dto.getModel() != null) car.setModel(dto.getModel());
        if (dto.getRegistrationNumber() != null) car.setRegistrationNumber(dto.getRegistrationNumber());

        Long driverId = dto.getDriverId();
        if (dto.getDriverId() != null) {
            final Driver driver = driverRepository.findById(driverId).orElse(null);
            if (driver == null) throw new EntityNotFoundException(Driver.class, driverId);
            car.setDriver(driver);
        }

        return toResponsePublicDTO(carRepository.save(car));
    }

    @Override
    public void delete(Long id) {
        final Car car = carRepository.findById(id).orElse(null);
        if (car == null) throw new EntityNotFoundException(Car.class, id);
        carRepository.delete(car);
    }

    @Override
    public CarDTO.Response.Public toResponsePublicDTO(final Car car) {
        CarDTO.Response.Public dto = new CarDTO.Response.Public();
        dto.setId(car.getId());
        dto.setMake(car.getMake());
        dto.setModel(car.getModel());
        dto.setRegistrationNumber(car.getRegistrationNumber());

        final Driver driver = car.getDriver();
        if (driver != null) {
            DriverDTO.Response.Public driverDto = new DriverDTO.Response.Public(
                    driver.getId(),
                    driver.getFirstName(),
                    driver.getMiddleName(),
                    driver.getLastName(),
                    driver.getMobilePhone()
            );

            dto.setDriver(driverDto);
        }

        return dto;
    }
}
