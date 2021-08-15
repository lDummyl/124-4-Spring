package com.example.demo.services;

import com.example.demo.converters.Converter;
import com.example.demo.dto.GarageDTO;
import com.example.demo.entities.Garage;
import com.example.demo.exceptions.CarDriverException;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.GarageRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final GarageRepository repository;
    private final CarRepository carRepository;
    private final Converter converter;

    public ResponseEntity<List<GarageDTO>> findAll() {
        var iterable = repository.findAll();
        var list = Streams.stream(iterable).collect(Collectors.toList());
        if (list.isEmpty())
            throw new CarDriverException("No garages.");
        else
            return new ResponseEntity<>(list.stream()
                    .map(converter::garageToDTO)
                    .collect(Collectors.toList())
                    , HttpStatus.OK);
    }

    public ResponseEntity<GarageDTO> findById(Integer id) {
        Optional<Garage> garage = repository.findById(id);
        if (garage.isEmpty())
            throw new CarDriverException("Garage with id:" + id + " don't exist.");
        else
            return new ResponseEntity<>(converter.garageToDTO(garage.get()), HttpStatus.OK);
    }

    public ResponseEntity<GarageDTO> save(GarageDTO garage) {
        if (garage == null)
            throw new CarDriverException("Can not save garage.");
        else {
            return new ResponseEntity<>(converter.garageToDTO(repository.save(converter.garageFromDTO(garage))), HttpStatus.OK);
        }
    }

    public ResponseEntity<GarageDTO> update(GarageDTO inputGarage) {
        if (!repository.existsById(inputGarage.getId()))
            throw new CarDriverException("Can not update garage.");
        else {
            var garage = repository.findById(inputGarage.getId()).get();
            garage.update(converter.garageFromDTO(inputGarage));
            return new ResponseEntity<>(converter.garageToDTO(repository.save(garage)), HttpStatus.OK);
        }
    }

    public ResponseEntity<GarageDTO> setCar(Integer garageId, Integer carId) {
        if (!(repository.existsById(garageId) || carRepository.existsById(carId)))
            throw new CarDriverException("Can not set a car for garage.");
        else {
            var garage = repository.findById(garageId).get();
            var car = carRepository.findById(carId).get();
            garage.getCars().add(car);
            car.getGarages().add(garage);
            carRepository.save(car);
            return new ResponseEntity<>(converter.garageToDTO(repository.save(garage)), HttpStatus.OK);
        }
    }

    public ResponseEntity<GarageDTO> removeCar(Integer garageId, Integer carId) {
        if (!(repository.existsById(garageId) || carRepository.existsById(carId)))
            throw new CarDriverException("Can not remove a car for garage.");
        else {
            var garage = repository.findById(garageId).get();
            var car = carRepository.findById(carId).get();
            if (!garage.getCars().contains(car))
                throw new CarDriverException("No such car in garage.");
            else {
                garage.getCars().remove(car);
                car.getGarages().remove(garage);
                carRepository.save(car);
                return new ResponseEntity<>(converter.garageToDTO(repository.save(garage)), HttpStatus.OK);
            }
        }
    }
}

