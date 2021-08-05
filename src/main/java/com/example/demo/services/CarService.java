package com.example.demo.services;

import com.example.demo.entity.Car;
import com.example.demo.model.request.CarRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CarService {
    Car saveCar(CarRequest car) throws RuntimeException;
    String updateCar(CarRequest car);
    Optional<Car> getCar(Long id);
    List<Car> getCars();
    String deleteCar(Long id);
    Set<Car> getAllByUserAge(Integer age);
    Page<Car> getPageableCarAndSort(int page, int size, String sortBy);
}
