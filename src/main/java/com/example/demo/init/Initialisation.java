package com.example.demo.init;

import com.example.demo.dataBases.repositories.CarRepository;
import com.example.demo.dataBases.repositories.UserRepo;
import com.example.demo.services.CarService;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Initialisation {

    private final UserService userService;
    private final CarService carService;

    public void userInit() {
        userService.init(1L, "Alex", 1984, 1L);
        userService.init(2L, "Mihail", 1989, 2L);
        userService.init(3L, "Roman", 1963, 3L);
        userService.init(4L, "Inna", 1990, 4L);
        userService.init(5L, "Daniil", 1974, 5L);
    }

    public void carInit() {
        carService.init("VW", 1L);
        carService.init("BMW", 2L);
        carService.init("LADA", 3L);
        carService.init("FORD", 4L);
        carService.init("VOLGA", 5L);
    }


}
