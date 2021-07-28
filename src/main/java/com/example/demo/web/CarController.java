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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService service;

    @GetMapping(value = {"", "/{id}"})
    public List<CarDTO> getById(@PathVariable Optional<Integer> id) {
        return id
                .map(integer -> Collections.singletonList(service.toDTO(service.getById(integer))))
                .orElseGet(() -> service.getAll().stream()
                        .map(service::toDTO)
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public CarDTO create(@RequestBody CarDTO dto) {
        return service.toDTO(service.create(dto));
    }

    @PutMapping
    public CarDTO update(@RequestBody CarDTO dto) {
        return service.toDTO(service.updateById(dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
