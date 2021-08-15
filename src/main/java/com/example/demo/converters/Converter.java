package com.example.demo.converters;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.DriverDTO;
import com.example.demo.dto.GarageDTO;
import com.example.demo.dto.OutDriverDTO;
import com.example.demo.entities.Car;
import com.example.demo.entities.Driver;
import com.example.demo.entities.Garage;
import com.example.demo.repositories.CarRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Converter {

    private final CarRepository carRepository;

    public OutDriverDTO driverToOut(Driver driver){
        OutDriverDTO outDriver = new OutDriverDTO();
        outDriver.setId(driver.getId());
        outDriver.setName(driver.getName());
        outDriver.setSurname(driver.getSurname());
        outDriver.setPhone(driver.getPhone());
        String[] dateArr = driver.getBirthDate().getTime().toString().split(" ");
        String date = dateArr[1] + " " + dateArr[2] + " " + dateArr[dateArr.length - 1];
        outDriver.setBirthDate(date);
        outDriver.setAddress(driver.getAddress());
        outDriver.setCars(driver.getCars().stream()
                .map(this::carToDTO)
                .collect(Collectors.toList()));
        return outDriver;
    }

    public DriverDTO driverToDTO(Driver driver){
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setSurname(driver.getSurname());
        driverDTO.setPhone(driver.getPhone());
        driverDTO.setBirthDate(driver.getBirthDate());
        driverDTO.setAddress(driver.getAddress());
        driverDTO.setCars(driver.getCars().stream()
                .map(this::carToDTO)
                .collect(Collectors.toList()));
        return driverDTO;
    }

    public CarDTO carToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setModel(car.getModel());
        carDTO.setGarages(car.getGarages().stream()
                .map(this::garageToDTO)
                .collect(Collectors.toList()));
        return carDTO;
    }

    @SneakyThrows
    public Driver driverFromDTO (DriverDTO driverDTO){
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setSurname(driverDTO.getSurname());
        driver.setPhone(driverDTO.getPhone());
        driver.setId(driverDTO.getId());
        driver.setBirthDate(driverDTO.getBirthDate());
        driver.setAddress(driverDTO.getAddress());
        driver.setCars(driverDTO.getCars().stream()
                .map(this::carFromDTO)
                .collect(Collectors.toList()));
        return driver;
    }

    public Car carFromDTO(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setModel(carDTO.getModel());
        car.setGarages(carDTO.getGarages().stream()
                .map(this::garageFromDTO)
                .collect(Collectors.toList()));
        return car;
    }

    public GarageDTO garageToDTO(Garage garage){
        GarageDTO garageDTO = new GarageDTO();
        garageDTO.setId(garage.getId());
        garageDTO.setAddress(garage.getAddress());
        garageDTO.setCarId(garage.getCars().stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        return garageDTO;
    }

    public Garage garageFromDTO(GarageDTO garageDTO){
        Garage garage = new Garage();
        garage.setId(garageDTO.getId());
        garage.setAddress(garageDTO.getAddress());
        garage.setCars(garageDTO.getCarId().stream()
                .map(carRepository::findById)
                .map(Optional::get)
                .collect(Collectors.toList()));
        return garage;
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

    public List<DriverDTO> driverIterableFromIterableDrivers(Iterable<Driver> drivers){
        return Streams.stream(drivers).map(this::driverToDTO).collect(Collectors.toList());
    }

    public List<Car> carListFromIterableDTO(Iterable<CarDTO> cars){
        return Streams.stream(cars).map(this::carFromDTO).collect(Collectors.toList());
    }

    public List<CarDTO> carIterableFromIterableCar(Iterable<Car> cars){
        return Streams.stream(cars).map(this::carToDTO).collect(Collectors.toList());
    }
}
