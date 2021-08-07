package com.example.demo.controllers;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final ObjectMapper objectMapper;

    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> findAll(){
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Integer id){
        return carService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CarDTO> save(@RequestBody CarDTO carDTO) {
        return carService.save(carDTO);
    }

    @PutMapping
    public ResponseEntity<CarDTO> update(@RequestBody CarDTO carDTO) {
        return carService.update(carDTO);
    }

    @PostMapping("/newList")
    public ResponseEntity<List<CarDTO>> saveAll(@RequestBody List<CarDTO> list) {
        return carService.saveAll(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> delete(@PathVariable Integer id){
        return carService.delete(id);
    }

    @GetMapping("/set/{carId}/{driverId}")
    public ResponseEntity<CarDTO> setDriver(@PathVariable Integer carId,
                            @PathVariable Integer driverId){
        return carService.addDriver(carId, driverId);
    }

    @GetMapping("/remove/{carId}/{driverId}")
    public ResponseEntity<CarDTO> removeDriver(@PathVariable Integer carId,
                               @PathVariable Integer driverId){
        return carService.removeDriver(carId, driverId);
    }

}
