package com.example.demo.services;

import com.example.demo.converters.Converter;
import com.example.demo.dto.CarDTO;
import com.example.demo.entities.Car;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final DriverRepository driverRepository;
    private final Converter converter;

    public Iterable<CarDTO> findAll(){
        return Streams.stream(repository.findAll())
                .map(converter::carToDTO)
                .collect(Collectors.toList());
    }

    public CarDTO findById(Integer id){
        Optional<Car> car = repository.findById(id);
        if(car.isEmpty())
            return new CarDTO();
        else
            return converter.carToDTO(car.get());
    }

    public CarDTO save(CarDTO carDTO){
        return converter.carToDTO(repository.save(converter.carFromDTO(carDTO)));
    }

    public Iterable<CarDTO> saveAll(Iterable<CarDTO> cars){
        var response = repository.saveAll(converter.carListFromIterableDTO(cars));
        return converter.carIterableFromIterableCar(response);
    }

    public CarDTO update(CarDTO car){
        var carDTO = findById(car.getId());
        carDTO.update(car);
        return save(carDTO);
    }

    public CarDTO addDriver(Integer carId, Integer driverId){
        var carOptional = repository.findById(carId);
        var driverOptional = driverRepository.findById(driverId);
        if(carOptional.isEmpty() || driverOptional.isEmpty())
            throw new RuntimeException("No such element!");
        else {
            var car = carOptional.get();
            var driver = driverOptional.get();
            car.getDrivers().add(driver);
            return converter.carToDTO(repository.save(car));
        }
    }

    public CarDTO removeDriver(Integer carId, Integer driverId){
        var carOptional = repository.findById(carId);
        var driverOptional = driverRepository.findById(driverId);
        if(carOptional.isEmpty() || driverOptional.isEmpty())
            throw new RuntimeException("No such element!");
        else {
            var car = carOptional.get();
            var driver = driverOptional.get();
            car.getDrivers().remove(driver);
            return converter.carToDTO(repository.save(car));
        }
    }

    public CarDTO delete(Integer id){
        if(repository.existsById(id)) {
            var car = findById(id);
            repository.deleteById(id);
            return car;
        }
        else throw new RuntimeException("No such element!");
    }
}
