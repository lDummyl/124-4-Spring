package com.example.demo.services;

import com.example.demo.entities.Driver;
import com.example.demo.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;

    public Driver create(String firstName, String lastName, Integer age) {
        Driver driver = new Driver();
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setAge(age);
        return repository.save(driver);
    }

    public Driver getById(Integer id) {
        Optional<Driver> driver = repository.findById(id);
        return driver.orElse(null);
    }

    public List<Driver> getAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Driver updateById(Integer id, String firstName, String lastName, Integer age) {
        Driver driver = repository.findById(id).orElse(new Driver());
        driver.setFirstName(firstName);
        driver.setLastName(lastName);
        driver.setAge(age);
        return repository.save(driver);
    }
}
