package com.example.demo.services;

import com.example.demo.converters.Converter;
import com.example.demo.dto.DriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.exceptions.CarDriverException;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import com.google.common.collect.Streams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository repository;
    private final Converter converter;

    public ResponseEntity<List<DriverDTO>> findAll(){
        return new ResponseEntity(Streams.stream(repository.findAll())
                .map(converter::driverToDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    public ResponseEntity<DriverDTO> findById(Integer id){
        Optional<Driver> driver = repository.findById(id);
        if(driver.isEmpty())
            throw new CarDriverException("Driver with id:" + id + " don't exist.");
        else
            return new ResponseEntity<>(converter.driverToDTO(driver.get()), HttpStatus.OK);
    }

    public ResponseEntity<DriverDTO> save(DriverDTO driverDTO){
        if(driverDTO == null)
            throw new CarDriverException("Can not save driver.");
        else
            return new ResponseEntity<>(converter.driverToDTO(repository.save(converter.driverFromDTO(driverDTO))),
                    HttpStatus.OK);
    }

    public ResponseEntity<List<DriverDTO>> saveAll(List<DriverDTO> drivers){
        if(drivers.isEmpty())
            throw new CarDriverException("Can not save drivers.");
        else {
            var response = repository.saveAll(converter.driverListFromIterableDTO(drivers));
            return new ResponseEntity<>(converter.driverIterableFromIterableDrivers(response), HttpStatus.OK);
        }
    }

    public ResponseEntity<DriverDTO> update(DriverDTO driver){
        if(repository.existsById(driver.getId())) {
            var driverDTO = (DriverDTO) findById(driver.getId()).getBody();
            driverDTO.update(driver);
            return new ResponseEntity<>(driverDTO, HttpStatus.OK);
        } else
            throw new CarDriverException("Can not update driver.");
    }

    public ResponseEntity<DriverDTO> delete(Integer id){
        if(repository.existsById(id)) {
            var driver = findById(id).getBody();
            repository.deleteById(id);
            return new ResponseEntity<>(driver, HttpStatus.OK);
        }
        else
            throw new CarDriverException("Can not delete driver.");
    }
}
