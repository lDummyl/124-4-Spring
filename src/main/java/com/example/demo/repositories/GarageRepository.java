package com.example.demo.repositories;

import com.example.demo.entities.Garage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageRepository extends CrudRepository<Garage, Integer> {
}
