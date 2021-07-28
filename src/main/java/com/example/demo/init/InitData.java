package com.example.demo.init;

import com.example.demo.services.CarService;
import com.example.demo.services.DriverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitData {

    private final CarService carService;
    private final DriverService driverService;

    @PostConstruct
    private void init() {
        driverService.create("Sergey", "Zhak", 34);
        driverService.create("Ivan", "Ivanov", 18);
        driverService.create("Petr", "Petrov", 50);
        driverService.create("Sidor", "Sidorov", 65);

        carService.create("E200", "Mersedes", "Cool modern car", 1);
        carService.create("Forrester", "Subaru", "Not so cool car", 1);
        carService.create("2101", "VAZ", "Kopeika", 3);
        carService.create("BELAZ", "BELAZ", "Samosval", 4);
    }
}
