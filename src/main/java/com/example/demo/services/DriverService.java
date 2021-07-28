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
            throw new CarDriverApiException("There is no such driver!");
        }
    }

    public List<Driver> getAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public Driver update(Integer id, String firstName, String lastName, Integer age) {
        Driver driver = repository.findById(id).orElseThrow(() -> new CarDriverApiException("There is no such driver!"));
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setAge(age);
        return repository.save(driver);
    }

    public DriverDTO toDTO(Driver driver) {
        if (driver == null) {
            throw new CarDriverApiException("Driver should not be null!");
        }
        DriverDTO dto = new DriverDTO();
        dto.setFirstName(driver.getFirstName());
        dto.setLastName(driver.getLastName());
        dto.setAge(driver.getAge());
        return dto;
    }
}
