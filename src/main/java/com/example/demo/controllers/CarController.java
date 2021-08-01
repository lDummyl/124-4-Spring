package com.example.demo.controllers;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final ObjectMapper objectMapper;

    @GetMapping("/all")
    public Iterable<CarDTO> findAll(){
        return carService.findAll();
    }

    @GetMapping("/id/{id}")
    public CarDTO findById(@PathVariable Integer id){
        return carService.findById(id);
    }

    @PostMapping("/new")
    public CarDTO save(@RequestBody String params) throws JsonProcessingException{
        return carService.save(objectMapper.readValue(params, CarDTO.class));
    }

    @PostMapping("/update")
    public CarDTO update(@RequestBody String params) throws JsonProcessingException{
        var car = objectMapper.readValue(params, CarDTO.class);
        return carService.update(car);
    }

    @PostMapping("/newList")
    public Iterable<CarDTO> saveAll(@RequestBody String params) throws JsonProcessingException{
        var list = Arrays.asList(objectMapper.readValue(params, CarDTO[].class));
        return carService.saveAll(list);
    }

    @GetMapping("/delete/{id}")
    public CarDTO delete(@PathVariable Integer id){
        return carService.delete(id);
    }

    @GetMapping("/set/{carId}/{driverId}")
    public CarDTO setDriver(@PathVariable Integer carId,
                            @PathVariable Integer driverId){
        return carService.addDriver(carId, driverId);
    }

    @GetMapping("/remove/{carId}/{driverId}")
    public CarDTO removeDriver(@PathVariable Integer carId,
                               @PathVariable Integer driverId){
        return carService.removeDriver(carId, driverId);
    }
}
