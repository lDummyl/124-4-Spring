package com.example.demo.controllers;

import com.example.demo.DTO.DriverDTO;
import com.example.demo.webServices.DriverWebService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverWebService service;

    public DriverController(DriverWebService service) {
        this.service = service;
    }

    @GetMapping(value = {"", "/{id}"})
    public List<DriverDTO> getByID(@PathVariable Optional<Integer> id) {
        return service.getByID(id);
    }

    @PostMapping
    public DriverDTO create(@RequestBody DriverDTO dto) {
        return service.create(dto);
    }

    @PutMapping
    public DriverDTO update(@RequestBody DriverDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
