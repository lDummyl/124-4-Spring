package com.example.demo.repository;

import com.example.demo.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c JOIN c.user u WHERE u.age > :age")
    Set<Car> getCarsByUserAge(@Param("age") Integer age);

    @Query("SELECT c FROM Car c")
    Page<Car> getAllSort(Pageable pageable);

}
