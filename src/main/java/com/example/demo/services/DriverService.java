package com.example.demo.services;

import com.example.demo.db.entities.DriverEntity;
import com.example.demo.dto.in.DriverIn;
import com.example.demo.dto.out.DriverOut;

import javax.transaction.Transactional;
import java.util.List;

public interface DriverService {
    @Transactional
    DriverEntity create(String firstName, String lastName, Integer age);

    DriverEntity getById(Integer id);

    List<DriverEntity> getAll();

    @Transactional
    void deleteById(Integer id);

    @Transactional
    DriverEntity update(Integer id, String firstName, String lastName, Integer age);

    DriverIn toInDTO(DriverEntity driverEntity);

    DriverOut toOutDTO(DriverEntity driverEntity);
}
