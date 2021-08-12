package com.example.demo.controllers;

import com.example.demo.dataBases.entity.CarEntity;
//import com.example.demo.db.ent.CarEntity;
import com.example.demo.dto.CarDetails;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @GetMapping("/id/{id}")
    public Optional<CarEntity> getCar(@PathVariable Long id){
        return carService.getCar(id);
    }

    @GetMapping("/model/{model}")
    public List<CarEntity> getByModel(@PathVariable String model){
        return carService.getByModel(model);
    }


    @GetMapping("/getAllCars")
    public List<CarEntity> getAllCars(){
        return carService.getAllCars();
    }

    @PostMapping("/createCar")
    public String carInitialisation (@RequestBody CarDetails carDetails){
        carService.create(carDetails);
        return "ok";
    }
    @DeleteMapping("/{id}")
    public String deletingCar(@PathVariable Long id){
        carService.deletingCar(id);
        return "ok";
    }

    @PutMapping("/updatingCar/{id}")
    public String updatingCar(@PathVariable Long id, @RequestBody CarDetails carDetails){
        carService.updatingCar(id, carDetails);
        return "ok";
    }

}
