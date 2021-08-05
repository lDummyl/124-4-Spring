package com.example.demo.db.repositories;

import com.example.demo.db.entities.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Integer> {
}
