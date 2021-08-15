package com.example.demo.repositories;

import com.example.demo.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Calendar;


@Repository
public interface DriverRepository extends CrudRepository<Driver, Integer> {
    Page<Driver> findAll(Pageable page);
    Page<Driver> readAllByBirthDateAfter(Calendar birthDate, Pageable page);
}
