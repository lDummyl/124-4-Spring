package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.model.request.CarRequest;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.ok().body(carService.getCars());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Car>> getCar(@PathVariable Long id) {
        return ResponseEntity.ok().body(carService.getCar(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCar(@RequestBody CarRequest carRequest) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/car/save").toUriString());

        try {
            Car car = carService.saveCar(carRequest);
            return ResponseEntity.created(uri).body(car);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCar(@RequestBody CarRequest car) {
        return ResponseEntity.ok().body(carService.updateCar(car));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(carService.deleteCar(id));
    }

}
