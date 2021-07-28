package com.example.demo.services;

import com.example.demo.dto.DriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.exceptions.CarDriverApiException;
import com.example.demo.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;

    private static final String NO_DRIVER_MESSAGE = "There is no such driver!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    @Transactional
    public Driver create(String firstName, String lastName, Integer age) {
        Driver driver = new Driver();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setAge(age);
        return repository.save(driver);
    }

    public Driver getById(Integer id) {
        Optional<Driver> driver = repository.findById(id);
        if (driver.isPresent()) {
            return driver.get();
        } else {
            throw new CarDriverApiException(NO_DRIVER_MESSAGE);
        }
    }

    public List<Driver> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!repository.existsById(id)) {
            throw new CarDriverApiException(NO_DRIVER_MESSAGE);
        }
        repository.deleteById(id);
    }

    @Transactional
    public Driver update(Integer id, String firstName, String lastName, Integer age) {
        Driver driver = repository.findById(id).orElseThrow(() -> new CarDriverApiException(NO_DRIVER_MESSAGE));
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setAge(age);
        return repository.save(driver);
    }

    public DriverDTO toDTO(Driver driver) {
        if (driver == null) {
            throw new CarDriverApiException(DTO_MUST_NOT_BE_NULL_MESSAGE);
        }
        DriverDTO dto = new DriverDTO();
        dto.setFirstName(driver.getFirstName());
        dto.setLastName(driver.getLastName());
        dto.setAge(driver.getAge());
        return dto;
    }
}
