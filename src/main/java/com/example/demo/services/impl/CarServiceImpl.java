package com.example.demo.services.impl;

import com.example.demo.db.entities.CarEntity;
import com.example.demo.db.entities.DriverEntity;
import com.example.demo.dto.in.CarIn;
import com.example.demo.dto.out.CarOut;
import com.example.demo.exceptions.CarDriverApiException;
import com.example.demo.db.repositories.CarRepository;
import com.example.demo.db.repositories.DriverRepository;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final DriverRepository driverRepository;
    private final ObjectMapper mapper;

    private static final String NO_CAR_MESSAGE = "There is no such car!";
    private static final String MAX_COUNT_REACHED = "There are enough cars already!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Value("${maxCars:0}")
    private Integer maxCars;

    @Transactional
    @Override
    public CarEntity create(String modelName, String carName, String description, Integer driverId) {
        if (maxCars > 0 && repository.count() >= maxCars) {
            throw new CarDriverApiException(MAX_COUNT_REACHED);
        }
        CarEntity carEntity = new CarEntity();
        carEntity.setModelName(modelName);
        carEntity.setCarName(carName);
        carEntity.setDescription(description);
        DriverEntity driver = driverRepository.findById(driverId).orElse(null);
        carEntity.setDriver(driver);
        return repository.save(carEntity);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new CarDriverApiException(NO_CAR_MESSAGE);
        }
        repository.deleteById(id);
    }

    @Override
    public CarEntity getById(Integer id) {
        Optional<CarEntity> car = repository.findById(id);
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new CarDriverApiException(NO_CAR_MESSAGE);
        }
    }

    @Override
    public List<CarEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public CarEntity update(Integer id, String modelName, String carName, String description, Integer driverId) {
        CarEntity carEntity = repository.findById(id).orElseThrow(() -> new CarDriverApiException(NO_CAR_MESSAGE));
        carEntity.setModelName(modelName);
        carEntity.setCarName(carName);
        carEntity.setDescription(description);
        DriverEntity driverEntity = driverRepository.findById(driverId).orElse(null);
        carEntity.setDriver(driverEntity);
        return repository.save(carEntity);
    }

    @Override
    public CarIn toInDTO(CarEntity carEntity) {
        return Optional.ofNullable(carEntity)
                .map(ent -> mapper.convertValue(ent, CarIn.class))
                .orElseThrow(() -> new CarDriverApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }

    @Override
    public CarOut toOutDTO(CarEntity carEntity) {
        return Optional.ofNullable(carEntity)
                .map(ent -> mapper.convertValue(ent, CarOut.class))
                .orElseThrow(() -> new CarDriverApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }
}
