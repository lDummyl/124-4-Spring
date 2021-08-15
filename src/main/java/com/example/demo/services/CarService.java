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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {


    private final CarRepository repository;
    private final Converter converter;


    public List<CarDTO> findAll(){
        return Streams.stream(repository.findAll())
                .map(converter::carToDTO)
                .collect(Collectors.toList());
    }

    public CarDTO findById(Integer id){
        Optional<Car> car = repository.findById(id);
        if(car.isEmpty())
            throw new CarDriverException("Car with id:" + id + " don't exist.");
        else
            return converter.carToDTO(car.get());
    }

    @Transactional
    public CarDTO save(CarDTO carDTO){
        if(carDTO == null) throw new CarDriverException("Can not save that car.");
        else return converter.carToDTO(repository.save(converter.carFromDTO(carDTO)));
    }

    @Transactional
    public List<CarDTO> saveAll(List<CarDTO> cars){
        if(cars.isEmpty()) throw new CarDriverException("Can not save cars.");
        else {
            var response = repository.saveAll(converter.carListFromIterableDTO(cars));
            return converter.carIterableFromIterableCar(response);
        }
    }

    @Transactional
    public CarDTO update(CarDTO car){
        if(car != null && repository.existsById(car.getId())) {
            var updateCar = converter.carToDTO(repository.findById(car.getId()).get());
            updateCar.update(car);
            return save(updateCar);
        } else
            throw new CarDriverException("Can not update that car.");
    }

    public void delete(Integer id){
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else
            throw new CarDriverException("Can not delete car.");
    }

}
