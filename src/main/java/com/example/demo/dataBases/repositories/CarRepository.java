package com.example.demo.dataBases.repositories;

import com.example.demo.dataBases.entity.CarEntity;
//import com.example.demo.db.ent.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
