package com.example.demo.controllers;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final ObjectMapper objectMapper;

    @GetMapping("/all")
    public ResponseEntity findAll(){
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        return carService.findById(id);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody String params) throws JsonProcessingException{
        return carService.save(objectMapper.readValue(params, CarDTO.class));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody String params) throws JsonProcessingException{
        var car = objectMapper.readValue(params, CarDTO.class);
        return carService.update(car);
    }

    @PostMapping("/newList")
    public ResponseEntity saveAll(@RequestBody String params) throws JsonProcessingException{
        var list = Arrays.asList(objectMapper.readValue(params, CarDTO[].class));
        return carService.saveAll(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        return carService.delete(id);
    }

    @GetMapping("/set/{carId}/{driverId}")
    public ResponseEntity setDriver(@PathVariable Integer carId,
                            @PathVariable Integer driverId){
        return carService.addDriver(carId, driverId);
    }

    @GetMapping("/remove/{carId}/{driverId}")
    public ResponseEntity removeDriver(@PathVariable Integer carId,
                               @PathVariable Integer driverId){
        return carService.removeDriver(carId, driverId);
    }
}
