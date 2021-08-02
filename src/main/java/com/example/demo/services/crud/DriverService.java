package com.example.demo.services.crud;

import com.example.demo.dto.DriverDTO;
import com.example.demo.entity.Driver;

import java.util.List;

/**
 * @author chervinko <br>
 * 30.07.2021
 */
public interface DriverService {
    DriverDTO.Response.Public get(Long id);

    List<DriverDTO.Response.Public> getAll();

    DriverDTO.Response.Public create(DriverDTO.Request.Create dto);

    DriverDTO.Response.Public update(DriverDTO.Request.Update dto);

    void delete(Long id);

    DriverDTO.Response.Public toResponsePublicDTO(final Driver driver);
}
