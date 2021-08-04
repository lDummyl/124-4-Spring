package com.example.demo.services;

import com.example.demo.converters.Converter;
import com.example.demo.dto.CarDTO;
import com.example.demo.entities.Car;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {


    private final CarRepository repository;
    private final DriverRepository driverRepository;
    private final Converter converter;

    public ResponseEntity findAll(){
        return new ResponseEntity(Streams.stream(repository.findAll())
                .map(converter::carToDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    public ResponseEntity findById(Integer id){
        Optional<Car> car = repository.findById(id);
        if(car.isEmpty())
            return new ResponseEntity<>("Car with id:" + id + " don't exist.", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(converter.carToDTO(car.get()), HttpStatus.OK);
    }

    public ResponseEntity save(CarDTO carDTO){
        if(carDTO == null) return new ResponseEntity<>("Can not save that car.", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity(converter.carToDTO(repository.save(converter.carFromDTO(carDTO))), HttpStatus.OK);
    }

    public ResponseEntity saveAll(Iterable<CarDTO> cars){
        var response = repository.saveAll(converter.carListFromIterableDTO(cars));
        return new ResponseEntity(converter.carIterableFromIterableCar(response), HttpStatus.OK);
    }

    public ResponseEntity update(CarDTO car){
        if(car != null) {
            var carDTO = (CarDTO) findById(car.getId()).getBody();
            carDTO.update(car);
            return save(carDTO);
        } else
            return new ResponseEntity<>("Can not update that car.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity addDriver(Integer carId, Integer driverId){
        var carOptional = repository.findById(carId);
        var driverOptional = driverRepository.findById(driverId);
        if(carOptional.isEmpty() || driverOptional.isEmpty())
            return new ResponseEntity("Can not add driver.", HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity("Can not remove driver.", HttpStatus.BAD_REQUEST);
        else {
            var car = carOptional.get();
            var driver = driverOptional.get();
            car.getDrivers().remove(driver);
            return new ResponseEntity(converter.carToDTO(repository.save(car)), HttpStatus.OK);
        }
    }

    public ResponseEntity delete(Integer id){
        if(repository.existsById(id)) {
            var car = (CarDTO) findById(id).getBody();
            repository.deleteById(id);
            return new ResponseEntity(car, HttpStatus.OK);
        }
        else return new ResponseEntity("Can no delete that car.", HttpStatus.BAD_REQUEST);
    }
}
