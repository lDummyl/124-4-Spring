package com.example.demo.controllers;

import com.example.demo.dto.GarageDTO;
import com.example.demo.services.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/garage")
public class GarageController {

    private final GarageService garageService;

    @GetMapping("/all")
    public ResponseEntity<List<GarageDTO>> findAll(){
        return garageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageDTO> findById(@PathVariable Integer id){
        return garageService.findById(id);
    }

    @PostMapping
    public ResponseEntity<GarageDTO> save(@RequestBody GarageDTO garage){
        return garageService.save(garage);
    }

    @PutMapping
    public ResponseEntity<GarageDTO> update(@RequestBody GarageDTO garage){
        return garageService.update(garage);
    }

    @GetMapping("/set/{garageId}/{carId}")
    public ResponseEntity<GarageDTO> setCar(@PathVariable Integer garageId,
                                         @PathVariable Integer carId){
        return garageService.setCar(garageId, carId);
    }

    @GetMapping("/remove/{garageId}/{carId}")
    public ResponseEntity<GarageDTO> removeCar(@PathVariable Integer garageId,
                                            @PathVariable Integer carId){
        return garageService.removeCar(garageId, carId);
    }
}
