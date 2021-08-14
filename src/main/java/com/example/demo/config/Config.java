package com.example.demo.config;

import com.example.demo.dataBases.entity.CarEntity;
import com.example.demo.dataBases.repositories.CarRepository;
import com.example.demo.dataBases.repositories.UserRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;


@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:app.yml")
public class Config {

    private final CarRepository carRepository;

    @Value("${a.carsCount}")
    int carsCount;

    public void carLimit() {
        int count = 0;
        List<CarEntity> all = carRepository.findAll();
        for (CarEntity entity : all) {
            count++;
            if (count >= carsCount) {
                throw new RuntimeException("The limit of cars is exceeded");
            }
        }
    }


}
