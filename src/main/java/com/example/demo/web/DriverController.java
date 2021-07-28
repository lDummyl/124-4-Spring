package com.example.demo.web;

import com.example.demo.dto.DriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.services.DriverService;
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
@RequestMapping("/driver")
public class DriverController {

    private final DriverService service;

    @GetMapping("/get/{id}")
    public Driver getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Driver> getAll() {
        return service.getAll();
    }

    @PostMapping("/create")
    public Driver create(@RequestBody DriverDTO dto) {
        return service.create(dto.getFirstName(), dto.getLastName(), dto.getAge());
    }

    @PostMapping("/update")
    public Driver update(@RequestBody DriverDTO dto) {
        return service.updateById(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getAge());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
