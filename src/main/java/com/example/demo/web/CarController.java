package com.example.demo.web;

import com.example.demo.dto.in.CarIn;
import com.example.demo.dto.out.CarOut;
import com.example.demo.webservices.CarWebService;
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
@RequestMapping("/car")
public class CarController {

    private final CarWebService service;

    @GetMapping("/all")
    public List<CarOut> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CarOut getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    public CarOut create(@RequestBody CarIn dto) {
        return service.create(dto);
    }

    @PutMapping
    public CarOut update(@RequestBody CarIn dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
