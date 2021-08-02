package com.example.demo.controller;

import com.example.demo.dto.DriverDTO;
import com.example.demo.services.crud.DriverDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author chervinko <br>
 * 27.07.2021
 */
@Slf4j
@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverDbService driverDbService;

    @GetMapping("/{id}")
    public DriverDTO.Response.Public getOne(@PathVariable Long id) {
        return driverDbService.get(id);
    }

    @GetMapping
    public List<DriverDTO.Response.Public> getAll() {
        return driverDbService.getAll();
    }

    @PostMapping
    public DriverDTO.Response.Public create(@Valid @RequestBody DriverDTO.Request.Create dto) {
        return driverDbService.create(dto);
    }

    @PutMapping
    public DriverDTO.Response.Public update(@Valid @RequestBody DriverDTO.Request.Update dto) {
        return driverDbService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        driverDbService.delete(id);
    }
}
