package com.example.demo.controllers;

import com.example.demo.dto.DriverDTO;
import com.example.demo.services.DriverService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver/")
public class DriverController {

    private final DriverService driverService;
    private final ObjectMapper objectMapper;

    @GetMapping("/all")
    public ResponseEntity findAll(){
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        return driverService.findById(id);
    }


    @PostMapping
    public ResponseEntity save(@RequestBody String params) throws JsonProcessingException{
        return driverService.save(objectMapper.readValue(params, DriverDTO.class));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody String params) throws JsonProcessingException{
        return driverService.update(objectMapper.readValue(params, DriverDTO.class));
    }

    @PostMapping("/newList")
    public ResponseEntity saveAll(@RequestBody String params) throws JsonProcessingException{
        var list = Arrays.asList(objectMapper.readValue(params, DriverDTO[].class));
        return driverService.saveAll(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        return driverService.delete(id);
    }
}
