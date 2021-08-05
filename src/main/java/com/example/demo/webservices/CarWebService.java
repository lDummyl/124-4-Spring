package com.example.demo.webservices;

import com.example.demo.dto.CarDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CarWebService {
    List<CarDTO> getById(Optional<Integer> id);

    @Transactional
    CarDTO create(CarDTO dto);

    @Transactional
    CarDTO update(CarDTO dto);

    @Transactional
    void delete(Integer id);
}
