package com.example.demo.services;

import com.example.demo.converters.Converter;
import com.example.demo.dto.CarDTO;
import com.example.demo.entities.Car;
import com.example.demo.entities.Driver;
import com.example.demo.exceptions.CarDriverException;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {


    private final CarRepository repository;
    private final DriverRepository driverRepository;
    private final Converter converter;

    public ResponseEntity<List<CarDTO>> findAll(){
        return new ResponseEntity(Streams.stream(repository.findAll())
                .map(converter::carToDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    public ResponseEntity<CarDTO> findById(Integer id){
        Optional<Car> car = repository.findById(id);
        if(car.isEmpty())
            throw new CarDriverException("Car with id:" + id + " don't exist.");
        else
            return new ResponseEntity<>(converter.carToDTO(car.get()), HttpStatus.OK);
    }

    public ResponseEntity<CarDTO> save(CarDTO carDTO){
        if(carDTO == null) throw new CarDriverException("Can not save that car.");
        else return new ResponseEntity<>(converter.carToDTO(repository.save(converter.carFromDTO(carDTO))), HttpStatus.OK);
    }

    public ResponseEntity<List<CarDTO>> saveAll(List<CarDTO> cars){
        if(cars.isEmpty()) throw new CarDriverException("Can not save cars.");
        else {
            var response = repository.saveAll(converter.carListFromIterableDTO(cars));
            return new ResponseEntity<>(converter.carIterableFromIterableCar(response), HttpStatus.OK);
        }
    }


    public ResponseEntity<CarDTO> update(CarDTO car){
        if(car != null) {
            var carDTO = (CarDTO) findById(car.getId()).getBody();
            carDTO.update(car);
            return new ResponseEntity<>(carDTO, HttpStatus.OK);
        } else
            throw new CarDriverException("Can not update that car.");
    }

    public ResponseEntity<CarDTO> addDriver(Integer carId, Integer driverId){
        var carOptional = repository.findById(carId);
        var driverOptional = driverRepository.findById(driverId);
        if(carOptional.isEmpty() || driverOptional.isEmpty())
            throw new CarDriverException("Can not add driver.");
        else {
            var car = carOptional.get();
            var driver = driverOptional.get();
            car.getDrivers().add(driver);
            return new ResponseEntity<>(converter.carToDTO(repository.save(car)), HttpStatus.OK);
        }
    }

    public ResponseEntity removeDriver(Integer carId, Integer driverId){
        var carOptional = repository.findById(carId);
        var driverOptional = driverRepository.findById(driverId);
        if(carOptional.isEmpty() || driverOptional.isEmpty())
            throw new CarDriverException("Can not remove driver from car.");
        else {
            var car = carOptional.get();
            var driver = driverOptional.get();
            car.getDrivers().remove(driver);
            return new ResponseEntity<>(converter.carToDTO(repository.save(car)), HttpStatus.OK);
        }
    }

    public ResponseEntity<CarDTO> delete(Integer id){
        if(repository.existsById(id)) {
            var car = (CarDTO) findById(id).getBody();
            repository.deleteById(id);
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        else throw new CarDriverException("Can not delete car.");
    }

}
