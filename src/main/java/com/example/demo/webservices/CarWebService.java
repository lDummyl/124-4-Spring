package com.example.demo.webservices;

import com.example.demo.dto.in.CarIn;
import com.example.demo.dto.out.CarOut;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CarWebService {
    List<CarOut> getById(Optional<Integer> id);

    @Transactional
    CarOut create(CarIn dto);

    @Transactional
    CarOut update(CarIn dto);

    @Transactional
    void delete(Integer id);
}
