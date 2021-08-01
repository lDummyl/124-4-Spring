package com.example.demo.services;

import com.example.demo.converters.Converter;
import com.example.demo.dto.DriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;
    private final CarRepository carRepository;
    private final Converter converter;

    public Iterable<DriverDTO> findAll(){
        return Streams.stream(repository.findAll())
                .map(converter::driverToDTO)
                .collect(Collectors.toList());
    }

    public DriverDTO findById(Integer id){
        Optional<Driver> driver = repository.findById(id);
        if(driver.isEmpty())
            return new DriverDTO();
        else
            return converter.driverToDTO(driver.get());
    }

    public DriverDTO save(DriverDTO driverDTO){
        return converter.driverToDTO(repository.save(converter.driverFromDTO(driverDTO)));
    }

    public Iterable<DriverDTO> saveAll(Iterable<DriverDTO> drivers){
        var response = repository.saveAll(converter.driverListFromIterableDTO(drivers));
        return converter.driverIterableFromIterableDrivers(response);
    }

    public DriverDTO update(DriverDTO driver){
        var driverDTO = findById(driver.getId());
        driverDTO.update(driver);
        return save(driverDTO);
    }

    public DriverDTO delete(Integer id){
        if(repository.existsById(id)) {
            var driver = findById(id);
            repository.deleteById(id);
            return driver;
        }
        else throw new RuntimeException("No such element!");
    }
}
