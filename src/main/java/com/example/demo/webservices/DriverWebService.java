package com.example.demo.webservices;

import com.example.demo.dto.in.DriverIn;
import com.example.demo.dto.out.DriverOut;

import javax.transaction.Transactional;
import java.util.List;

public interface DriverWebService {
    DriverOut getById(Integer id);

    List<DriverOut> getAll();

    @Transactional
    DriverOut create(DriverIn dto);

    @Transactional
    DriverOut update(DriverIn dto);

    @Transactional
    void delete(Integer id);
}
