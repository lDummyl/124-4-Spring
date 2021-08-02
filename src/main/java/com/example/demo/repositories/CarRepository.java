package com.example.demo.repositories;

import com.example.demo.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
