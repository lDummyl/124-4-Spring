package com.example.demo.services;

import com.example.demo.converters.Converter;
import com.example.demo.dto.DriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;
    private final CarRepository carRepository;
    private final Converter converter;

    public ResponseEntity findAll(){
        return new ResponseEntity(Streams.stream(repository.findAll())
                .map(converter::driverToDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    public ResponseEntity findById(Integer id){
        Optional<Driver> driver = repository.findById(id);
        if(driver.isEmpty())
            return new ResponseEntity("Driver with id:" + id + " don't exist.", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(converter.driverToDTO(driver.get()), HttpStatus.OK);
    }

    public ResponseEntity save(DriverDTO driverDTO){
        if(driverDTO == null)
            return new ResponseEntity("Can not save driver.", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity(converter.driverToDTO(repository.save(converter.driverFromDTO(driverDTO))),
                    HttpStatus.OK);
    }

    public ResponseEntity saveAll(Iterable<DriverDTO> drivers){
        var response = repository.saveAll(converter.driverListFromIterableDTO(drivers));
        return new ResponseEntity(converter.driverIterableFromIterableDrivers(response), HttpStatus.OK);
    }

    public ResponseEntity update(DriverDTO driver){
        if(repository.existsById(driver.getId())) {
            var driverDTO = (DriverDTO) findById(driver.getId()).getBody();
            driverDTO.update(driver);
            return new ResponseEntity(save(driverDTO), HttpStatus.OK);
        } else
            return new ResponseEntity("Can not update driver.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity delete(Integer id){
        if(repository.existsById(id)) {
            var driver = findById(id).getBody();
            repository.deleteById(id);
            return new ResponseEntity(driver, HttpStatus.OK);
        }
        else return new ResponseEntity("Can not delete driver.", HttpStatus.BAD_REQUEST);
    }
}
