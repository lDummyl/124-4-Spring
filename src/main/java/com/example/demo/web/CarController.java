package com.example.demo.web;

import com.example.demo.dto.CarDTO;
import com.example.demo.webservices.CarWebService;
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

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarWebService service;

    @GetMapping(value = {"", "/{id}"})
    public List<CarDTO> getById(@PathVariable Optional<Integer> id) {
        return service.getById(id);
    }

    @PostMapping
    public CarDTO create(@RequestBody CarDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public CarDTO update(@RequestBody CarDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
