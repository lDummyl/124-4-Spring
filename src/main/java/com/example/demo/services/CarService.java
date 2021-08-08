package com.example.demo.services;

import com.example.demo.db.entities.CarEntity;
import com.example.demo.dto.in.CarIn;
import com.example.demo.dto.out.CarOut;

import javax.transaction.Transactional;
import java.util.List;

public interface CarService {
    @Transactional
    CarEntity create(String modelName, String carName, String description, Integer driverId);

    @Transactional
    void deleteById(Integer id);

    CarEntity getById(Integer id);

    List<CarEntity> getAll();

    @Transactional
    CarEntity update(Integer id, String modelName, String carName, String description, Integer driverId);

    CarIn toInDTO(CarEntity carEntity);

    CarOut toOutDTO(CarEntity carEntity);
}
