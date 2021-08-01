package com.example.demo.controllers;

import com.example.demo.DTO.CarDTO;
import com.example.demo.webServices.CarWebService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarWebService service;

    public CarController(CarWebService service) {
        this.service = service;
    }

    @GetMapping(value = {"", "{/id}"})
    public List<CarDTO> getByID(@PathVariable Optional<Long> id){
        return service.getByID(id);
    }

    @PostMapping
    public CarDTO create(@RequestBody CarDTO dto){
        return service.create(dto);
    }

    @PutMapping
    public CarDTO update(@RequestBody CarDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("{/id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
