package com.example.demo.controllers;

import com.example.demo.dto.DriverDTO;
import com.example.demo.services.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver/")
public class DriverController {

    private final DriverService driverService;
    private final ObjectMapper objectMapper;

    @GetMapping("/all")
    public ResponseEntity<List<DriverDTO>> findAll(){
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> findById(@PathVariable Integer id){
        return driverService.findById(id);
    }


    @PostMapping
    public ResponseEntity<DriverDTO> save(@RequestBody DriverDTO driverDTO) {
        return driverService.save(driverDTO);
    }

    @PutMapping
    public ResponseEntity<DriverDTO> update(@RequestBody DriverDTO driverDTO) {
        return driverService.update(driverDTO);
    }

    @PostMapping("/newList")
    public ResponseEntity<List<DriverDTO>> saveAll(@RequestBody List<DriverDTO> list) {
        return driverService.saveAll(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DriverDTO> delete(@PathVariable Integer id){
        return driverService.delete(id);
    }

}
