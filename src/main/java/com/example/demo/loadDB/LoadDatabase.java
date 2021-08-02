package com.example.demo.loadDB;

import com.example.demo.servises.CarService;
import com.example.demo.servises.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final CarService carService;
    private final DriverService driverService;

    public LoadDatabase(CarService carService, DriverService driverService) {
        this.carService = carService;
        this.driverService = driverService;
    }

    @PostConstruct
    @Bean
    CommandLineRunner initDriversDatabase() {
        return args -> {
            log.info("Preloading driver" + driverService.create("Andrey", "Ivanov", 22).toString());
            log.info("Preloading driver" + driverService.create("Ivan", "Petrov", 28));
            log.info("Preloading driver" + driverService.create("Aleksey", "Smirnov", 35));
        };
    }

    @PostConstruct
    @Bean
    CommandLineRunner initCarsDatabase() {
        return args -> {
            log.info("Preloading car" + carService.create("Suzuki", "Jimny", "Japan's NIVA", 1));
            log.info("Preloading car" + carService.create("Volvo", "XC90", "It's chinese now", 2));
            log.info("Preloading car" + carService.create("Volkswagen", "Golf2", "Old german legend", 1));
            log.info("Preloading car" + carService.create("VAZ", "2101", "Old USSR legend", 3));
        };
    }
}
