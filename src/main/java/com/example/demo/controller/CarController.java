package com.example.demo.controller;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.crud.CarDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * @author chervinko <br>
 * 27.07.2021
 */
@Slf4j
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarDbService carDbService;

    @GetMapping("/{id}")
    public  CarDTO.Response.Public getOne(@PathVariable Long id) {
        return carDbService.get(id);
    }

    @GetMapping
    public Collection<CarDTO.Response.Public> getAll() {
        return carDbService.getAll();
    }

    @PostMapping
    public CarDTO.Response.Public create(@Valid @RequestBody CarDTO.Request.Create dto) {
        return carDbService.create(dto);
    }

    @PutMapping
    public CarDTO.Response.Public update(@Valid @RequestBody CarDTO.Request.Update dto) {
        return carDbService.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        carDbService.delete(id);

        return ResponseEntity.ok("success");
    }
}
