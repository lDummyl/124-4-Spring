package com.example.demo.web;

import com.example.demo.dto.in.DriverIn;
import com.example.demo.dto.out.DriverOut;
import com.example.demo.webservices.DriverWebService;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverController {

    private final DriverWebService service;

    @GetMapping("/all")
    public List<DriverOut> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DriverOut getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public DriverOut create(@RequestBody DriverIn dto) {
        return service.create(dto);
    }

    @PutMapping
    public DriverOut update(@RequestBody DriverIn dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
