package com.example.demo.repositories;

import com.example.demo.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
