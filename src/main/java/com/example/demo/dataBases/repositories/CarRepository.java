package com.example.demo.dataBases.repositories;

import com.example.demo.dataBases.entity.CarEntity;
//import com.example.demo.db.ent.CarEntity;
//import org.hibernate.mapping.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {



        List<CarEntity> findByModel(String model);

}
