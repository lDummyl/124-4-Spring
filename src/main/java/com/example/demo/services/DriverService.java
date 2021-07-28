package com.example.demo.services;

import com.example.demo.entities.Driver;
import com.example.demo.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;

    public Driver create(String firstName, String lastName, Long age) {
        Driver driver = new Driver();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setAge(age);
        return repository.save(driver);
    }

    public Driver getById(Long id) {
        return repository.getById(id);
    }

    public List<Driver> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Driver updateById(Long id, String firstName, String lastName, Long age) {
        Driver driver = repository.getById(id);
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setAge(age);
        return repository.save(driver);
    }
}
