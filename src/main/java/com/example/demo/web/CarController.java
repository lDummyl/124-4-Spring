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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService service;

    @GetMapping(value = {"", "/{id}"})
    public List<Car> getById(@PathVariable(name = "id")/* FIXME: 28.07.2021 redundant*/ Optional<Integer> id) {
        return id
                .map(integer -> Collections.singletonList(service.getById(integer)))
                .orElseGet(service::getAll);
    }

    @PostMapping("")// FIXME: 28.07.2021 redundant
    public Car/*fixme return DTO here and everywhere, use ObjectMapper inject it to service*/ create(@RequestBody CarDTO dto) {
        return service.create(dto.getModelName(), dto.getCarName(), dto.getDescription(), dto.getDriverId());
    }

    @PutMapping("")
    public Car update(@RequestBody CarDTO dto) {
        return service.updateById(dto.getId(), dto.getModelName(), dto.getCarName(), dto.getDescription(), dto.getDriverId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
