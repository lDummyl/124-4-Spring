package com.example.demo.services.impl;

import com.example.demo.dto.CarDTO;
import com.example.demo.db.entities.CarEntity;
import com.example.demo.db.entities.DriverEntity;
import com.example.demo.exceptions.CarDriverApiException;
import com.example.demo.db.repositories.CarRepository;
import com.example.demo.db.repositories.DriverRepository;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private static final String NO_CAR_MESSAGE = "There is no such car!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    @Override
    public CarEntity create(String modelName, String carName, String description, Integer driverId) {
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
    public CarDTO toDTO(CarEntity carEntity) {
        if (carEntity == null) {
            throw new CarDriverApiException(DTO_MUST_NOT_BE_NULL_MESSAGE);
        }
        CarDTO dto = new CarDTO();
        dto.setCarName(carEntity.getCarName());
        dto.setModelName(carEntity.getModelName());
        dto.setDescription(carEntity.getDescription());
        dto.setDriverId(Optional.ofNullable(carEntity.getDriver()).map(DriverEntity::getId).orElse(null));
        dto.setId(carEntity.getId());
        return dto;
    }
}