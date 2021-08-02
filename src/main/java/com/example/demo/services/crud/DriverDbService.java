package com.example.demo.services.crud;

import com.example.demo.dto.DriverDTO;
import com.example.demo.entity.Car;
import com.example.demo.entity.Driver;
import com.example.demo.repository.DriverRepository;
import com.example.demo.services.crud.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chervinko <br>
 * 28.07.2021
 */
@Service
@RequiredArgsConstructor
public class DriverDbService implements DriverService {
    private final DriverRepository driverRepository;

    @Override
    public DriverDTO.Response.Public get(Long id) {
        final Driver driver = driverRepository.findById(id).orElse(null);
        if (driver == null) return null;

        return toResponsePublicDTO(driver);
    }

    @Override
    public List<DriverDTO.Response.Public> getAll() {
        List<DriverDTO.Response.Public> dtos = new ArrayList<>();
        driverRepository.findAll().forEach(driver -> dtos.add(toResponsePublicDTO(driver)));

        return dtos;
    }

    @Override
    public DriverDTO.Response.Public create(DriverDTO.Request.Create dto) {
        Driver driver = new Driver();
        driver.setFirstName(dto.getFirstName());
        driver.setMiddleName(dto.getMiddleName());
        driver.setLastName(dto.getLastName());
        driver.setMobilePhone(dto.getMobilePhone());

        return toResponsePublicDTO(driverRepository.save(driver));
    }

    @Override
    public DriverDTO.Response.Public update(DriverDTO.Request.Update dto) {
        final Driver driver = driverRepository.findById(dto.getId()).orElse(null);
        if (driver == null) throw new EntityNotFoundException(Driver.class, dto.getId());

        if (dto.getFirstName() != null) driver.setFirstName(dto.getFirstName());
        if (dto.getMiddleName() != null) driver.setMiddleName(dto.getMiddleName());
        if (dto.getLastName() != null) driver.setLastName(dto.getLastName());
        if (dto.getMobilePhone() != null) driver.setMobilePhone(dto.getMobilePhone());

        return toResponsePublicDTO(driverRepository.save(driver));
    }

    @Override
    public void delete(Long id) {
        final Driver driver = driverRepository.findById(id).orElse(null);
        if (driver == null) throw new EntityNotFoundException(Driver.class, id);
        driverRepository.delete(driver);
    }

    @Override
    public DriverDTO.Response.Public toResponsePublicDTO(final Driver driver) {
        DriverDTO.Response.Public dto = new DriverDTO.Response.Public(
                driver.getId(),
                driver.getFirstName(),
                driver.getMiddleName(),
                driver.getLastName(),
                driver.getMobilePhone()
        );

        if (driver.getCars() != null) {
            dto.setCar(driver.getCars().stream().map(Car::getId).toArray(Long[]::new));
        }

        return dto;
    }
}
