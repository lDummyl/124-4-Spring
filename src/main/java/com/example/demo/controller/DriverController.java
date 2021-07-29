package com.example.demo.controller;

import com.example.demo.entity.Driver;
import com.example.demo.model.request.DriverUpdate;
import com.example.demo.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAll() {
        return ResponseEntity.ok().body(driverService.getDrivers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Driver>> getDriver(@PathVariable Long id) {
        return ResponseEntity.ok().body(driverService.getDriver(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Driver> saveDriver(@RequestBody Driver driver) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/driver/save").toUriString());
        return ResponseEntity.created(uri).body(driverService.saveDriver(driver));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateDriver(@RequestBody DriverUpdate driver) {
        return ResponseEntity.ok().body(driverService.updateDriver(driver));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(driverService.deleteDriver(id));
    }

}
