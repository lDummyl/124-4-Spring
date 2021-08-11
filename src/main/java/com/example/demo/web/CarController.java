package com.example.demo.web;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Slf4j
@Validated
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
    public ResponseEntity<CarDTO> read(@NotNull @Min(1) @PathVariable Long id) {
        return new ResponseEntity<>(carService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CarDTO newCar) {
        newCar = carService.add(newCar);
        return new ResponseEntity<>("Car created with id " + newCar.getId() , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@NotNull @Min(1) @PathVariable Long id, @Valid @RequestBody CarDTO newCar) {
        newCar.setId(id);
        newCar = carService.update(newCar);
        return new ResponseEntity<>("Car " + id.toString() + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@NotNull @Min(1) @PathVariable Long id) {
        carService.delete(id);
        return new ResponseEntity<>("Car " + id.toString() + " deleted", HttpStatus.OK);
    }
}
