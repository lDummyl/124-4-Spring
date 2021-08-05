package com.example.demo.webservices;

import com.example.demo.dto.DriverDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface DriverWebService {
    List<DriverDTO> getById(Optional<Integer> id);

    @Transactional
    DriverDTO create(DriverDTO dto);

    @Transactional
    DriverDTO update(DriverDTO dto);

    @Transactional
    void delete(Integer id);
}
