package com.example.demo.services.impl;

import com.example.demo.dto.DriverDTO;
import com.example.demo.db.entities.DriverEntity;
import com.example.demo.exceptions.CarDriverApiException;
import com.example.demo.db.repositories.DriverRepository;
import com.example.demo.services.DriverService;
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
    public DriverDTO toDTO(DriverEntity driverEntity) {
        if (driverEntity == null) {
            throw new CarDriverApiException(DTO_MUST_NOT_BE_NULL_MESSAGE);
        }
        DriverDTO dto = new DriverDTO();
        dto.setId(driverEntity.getId());
        dto.setFirstName(driverEntity.getFirstName());
        dto.setLastName(driverEntity.getLastName());
        dto.setAge(driverEntity.getAge());
        return dto;
    }
}
