package com.example.demo.web;

import com.example.demo.dto.DriverDTO;
import com.example.demo.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService service;

    @GetMapping
    public ResponseEntity<Set<DriverDTO>> readAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> read(@NotNull @Min(1) @PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody DriverDTO newDriver) {
        newDriver = service.add(newDriver);
        return new ResponseEntity<>("Driver created with id " + newDriver.getId(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@NotNull @Min(1) @PathVariable Long id, @Valid @RequestBody DriverDTO newDriver) {
        newDriver.setId(id);
        newDriver = service.update(newDriver);
        return new ResponseEntity<>("Driver " + id.toString() + " updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@NotNull @Min(1) @PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("Driver " + id.toString() + " deleted", HttpStatus.OK);
    }
}
