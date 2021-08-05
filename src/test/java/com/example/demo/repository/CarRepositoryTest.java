package com.example.demo.repository;

import com.example.demo.entity.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
@EntityScan({"com.example.demo.entity"})
public class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @Test
    public void findByUsernameTest() {
        Optional<Car> byId = carRepository.findById(1L);
        assertTrue(byId.isPresent());
    }

    @Test
    public void getCarsByUserAgeTest() {
        Set<Car> carsByUserAge = carRepository.getCarsByUserAge(1);
        assert (carsByUserAge.size() > 0);
    }

}