package com.example.demo.services;

import com.example.demo.converters.Converter;
import com.example.demo.dto.DriverDTO;
import com.example.demo.dto.OutDriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.exceptions.CarDriverException;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;
    private final CarRepository carRepository;
    private final Converter converter;

    @Value("${values.max_number:5}")
    private int maxNumber;

    public List<DriverDTO> findAll() {
        return Streams.stream(repository.findAll())
                .map(converter::driverToDTO)
                .collect(Collectors.toList());
    }

    public DriverDTO findById(Integer id) {
        Optional<Driver> driver = repository.findById(id);
        if (driver.isEmpty())
            throw new CarDriverException("Driver with id:" + id + " don't exist.");
        else
            return converter.driverToDTO(driver.get());
    }

    @Transactional
    public OutDriverDTO save(DriverDTO driverDTO) {
        if (driverDTO == null)
            throw new CarDriverException("Can not save driver.");
        else if(driverDTO.getCars().size() >= maxNumber)
            throw new CarDriverException("Driver can't have more than " + maxNumber + " cars.");
        else
            return converter.driverToOut(repository.save(converter.driverFromDTO(driverDTO)));
    }

    @Transactional
    public List<OutDriverDTO> saveAll(List<DriverDTO> drivers) {
        if (drivers.isEmpty())
            throw new CarDriverException("Can not save drivers.");
        if (drivers.stream().anyMatch(x -> x.getCars().size() >= maxNumber))
            throw new CarDriverException("Driver can't have more than " + maxNumber + " cars.");
        else {
            var response = repository.saveAll(converter.driverListFromIterableDTO(drivers));
            return Streams.stream(response).map(converter::driverToOut).collect(Collectors.toList());
        }
    }

    @Transactional
    public OutDriverDTO update(DriverDTO driver) {
        if (repository.existsById(driver.getId()) && driver != null) {
            var updateDriver = converter.driverToDTO(repository.findById(driver.getId()).get());
            updateDriver.update(driver);
            return save(updateDriver);
        } else if(driver.getCars().size() >= maxNumber)
            throw new CarDriverException("Driver can't have more than " + maxNumber + " cars.");
        else
            throw new CarDriverException("Can not update driver.");
    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else
            throw new CarDriverException("Can not delete driver with id: " + id);
    }

    public Page<DriverDTO> getPage(int pageNumber, int pageSize, String sortBy) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)))
                .map(converter::driverToDTO);
    }

    public Page<DriverDTO> getPageDriversAfterAge(int pageNumber, int pageSize, int age) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        return repository.readAllByBirthDateAfter(cal, page).map(converter::driverToDTO);
    }

    @Transactional
    public DriverDTO setCar(int driverId, int carId){
        var driverOptional = repository.findById(driverId);
        var carOptional = carRepository.findById(carId);
        if(driverOptional.isEmpty() || carOptional.isEmpty()){
            throw new CarDriverException("Can not set car.");
        } else {
            var driver = driverOptional.get();
            var car = carOptional.get();
            driver.getCars().add(car);
            repository.save(driver);
            return converter.driverToDTO(driver);
        }
    }

    @Transactional
    public DriverDTO removeCar(int driverId, int carId){
        var driverOptional = repository.findById(driverId);
        var carOptional = carRepository.findById(carId);
        if(carOptional.isEmpty() || driverOptional.isEmpty()){
            throw new CarDriverException("Can not remove car.");
        } else {
            var driver = driverOptional.get();
            var car = carOptional.get();
            driver.getCars().remove(car);
            repository.save(driver);
            return converter.driverToDTO(driver);
        }
    }
}
