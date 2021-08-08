package com.example.demo.services.impl;

import com.example.demo.db.entities.DriverEntity;
import com.example.demo.dto.in.DriverIn;
import com.example.demo.dto.out.DriverOut;
import com.example.demo.exceptions.CarDriverApiException;
import com.example.demo.db.repositories.DriverRepository;
import com.example.demo.services.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;
    private final ObjectMapper mapper;

    private static final String NO_DRIVER_MESSAGE = "There is no such driver!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    @Override
    public DriverEntity create(String firstName, String lastName, Integer age) {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setFirstName(firstName);
        driverEntity.setLastName(lastName);
        driverEntity.setAge(age);
        return repository.save(driverEntity);
    }

    @Override
    public DriverEntity getById(Integer id) {
        Optional<DriverEntity> driver = repository.findById(id);
        if (driver.isPresent()) {
            return driver.get();
        } else {
            throw new CarDriverApiException(NO_DRIVER_MESSAGE);
        }
    }

    @Override
    public List<DriverEntity> getAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new CarDriverApiException(NO_DRIVER_MESSAGE);
        }
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public DriverEntity update(Integer id, String firstName, String lastName, Integer age) {
        DriverEntity driverEntity = repository.findById(id).orElseThrow(() -> new CarDriverApiException(NO_DRIVER_MESSAGE));
        driverEntity.setFirstName(firstName);
        driverEntity.setLastName(lastName);
        driverEntity.setAge(age);
        return repository.save(driverEntity);
    }

    @Override
    public DriverIn toInDTO(DriverEntity entity) {
        return Optional.ofNullable(entity)
                .map(ent -> mapper.convertValue(ent, DriverIn.class))
                .orElseThrow(() -> new CarDriverApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }

    @Override
    public DriverOut toOutDTO(DriverEntity entity) {
        return Optional.ofNullable(entity)
                .map(ent -> mapper.convertValue(ent, DriverOut.class))
                .orElseThrow(() -> new CarDriverApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }
}
