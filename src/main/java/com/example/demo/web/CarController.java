package com.example.demo.web;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.UserDetails;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Collection;
import java.util.Set;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<Set<CarDTO>> readAll() {
        return new ResponseEntity<>(carService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> read(@PathVariable @Validated Long id) {
        return new ResponseEntity<>(carService.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CarDTO newCar) {
        carService.add(newCar);
        return new ResponseEntity<>("Car created", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable @Validated Long id, @RequestBody CarDTO newCar) {
        newCar.setId(id);
        carService.update(newCar);
        return new ResponseEntity<>("Car" + id.toString() + "updated.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Validated Long id) {
        carService.delete(id);
        return new ResponseEntity<>("Car" + id.toString() + "deleted.", HttpStatus.OK);
    }
}
