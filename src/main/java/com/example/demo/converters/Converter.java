package com.example.demo.converters;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.DriverDTO;
import com.example.demo.entities.Car;
import com.example.demo.entities.Driver;
import com.google.common.collect.Streams;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {

    public DriverDTO driverToDTO(Driver driver){
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setSurname(driver.getSurname());
        driverDTO.setPhone(driver.getPhone());
        return driverDTO;
    }

    public CarDTO carToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setModel(car.getModel());
        carDTO.setDrivers(this.driverListToDTO(car.getDrivers()));
        return carDTO;
    }

    public Driver driverFromDTO (DriverDTO driverDTO){
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setSurname(driverDTO.getSurname());
        driver.setPhone(driverDTO.getPhone());
        driver.setId(driverDTO.getId());
        return driver;
    }

    public Car carFromDTO(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setModel(carDTO.getModel());
        car.setDrivers(this.driverListFromDTO(carDTO.getDrivers()));
        return car;
    }

    public List<Driver> driverListFromDTO(List<DriverDTO> drivers) {
        return drivers.stream().map(this::driverFromDTO).collect(Collectors.toList());
    }

    public List<DriverDTO> driverListToDTO(List<Driver> drivers) {
        return drivers.stream().map(this::driverToDTO).collect(Collectors.toList());
    }

    public List<Driver> driverListFromIterableDTO(Iterable<DriverDTO> drivers){
        return Streams.stream(drivers).map(this::driverFromDTO).collect(Collectors.toList());
    }

    public Iterable<DriverDTO> driverIterableFromIterableDrivers(Iterable<Driver> drivers){
        return Streams.stream(drivers).map(this::driverToDTO).collect(Collectors.toList());
    }

    public List<Car> carListFromIterableDTO(Iterable<CarDTO> cars){
        return Streams.stream(cars).map(this::carFromDTO).collect(Collectors.toList());
    }

    public Iterable<CarDTO> carIterableFromIterableCar(Iterable<Car> cars){
        return Streams.stream(cars).map(this::carToDTO).collect(Collectors.toList());
    }
}
