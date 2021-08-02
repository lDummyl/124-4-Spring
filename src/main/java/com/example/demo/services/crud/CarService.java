package com.example.demo.services.crud;

import com.example.demo.dto.CarDTO;
import com.example.demo.entity.Car;

import java.util.List;

/**
 * @author chervinko <br>
 * 28.07.2021
 */
public interface CarService {
    CarDTO.Response.Public get(Long id);

    List<CarDTO.Response.Public> getAll();

    CarDTO.Response.Public create(CarDTO.Request.Create dto);

    CarDTO.Response.Public update(CarDTO.Request.Update dto);

    void delete(Long id);

    CarDTO.Response.Public toResponsePublicDTO(final Car car);
}
