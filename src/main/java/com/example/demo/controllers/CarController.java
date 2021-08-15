package com.example.demo.controllers;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> findAll(){
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> findById(@PathVariable Integer id){
        return new ResponseEntity<>(carService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarDTO> save(@RequestBody CarDTO carDTO) {
        return new ResponseEntity<>(carService.save(carDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CarDTO> update(@RequestBody CarDTO carDTO) {
        return new ResponseEntity<>(carService.update(carDTO), HttpStatus.OK);
    }

    @PostMapping("/newList")
    public ResponseEntity<List<CarDTO>> saveAll(@RequestBody List<CarDTO> list) {
        return new ResponseEntity<>(carService.saveAll(list), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        carService.delete(id);
        return new ResponseEntity<>("Car with id: " + id + " deleted.", HttpStatus.OK);
    }

}
