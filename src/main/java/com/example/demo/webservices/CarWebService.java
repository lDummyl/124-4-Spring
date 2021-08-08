package com.example.demo.webservices;

import com.example.demo.dto.in.CarIn;
import com.example.demo.dto.out.CarOut;

import javax.transaction.Transactional;
import java.util.List;

public interface CarWebService {
    CarOut getById(Integer id);

    List<CarOut> getAll();

    @Transactional
    CarOut create(CarIn dto);

    @Transactional
    CarOut update(CarIn dto);

    @Transactional
    void delete(Integer id);
}
