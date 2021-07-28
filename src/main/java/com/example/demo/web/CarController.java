package com.example.demo.web;

import com.example.demo.dto.CarDTO;
import com.example.demo.entities.Car;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService service;

    @GetMapping("/get/{id}")
    public Car getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Car> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public Car create(@RequestBody CarDTO dto) {
        return service.create(dto.getModelName(), dto.getCarName(), dto.getDescription(), dto.getDriverId());
    }

    @PostMapping("/update")
    public Car update(@RequestBody CarDTO dto) {
        return service.updateById(dto.getId(), dto.getModelName(), dto.getCarName(), dto.getDescription(), dto.getDriverId());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
