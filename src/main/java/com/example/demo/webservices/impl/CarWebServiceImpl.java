package com.example.demo.webservices.impl;

import com.example.demo.dto.in.CarIn;
import com.example.demo.dto.out.CarOut;
import com.example.demo.services.CarService;
import com.example.demo.webservices.CarWebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarWebServiceImpl implements CarWebService {

    private final CarService service;

    @Override
    public CarOut getById(Integer id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<CarOut> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CarOut create(CarIn dto) {
        return service.toOutDTO(service.create(
                dto.getModelName(),
                dto.getCarName(),
                dto.getDescription(),
                dto.getDriverId()
        ));
    }

    @Transactional
    @Override
    public CarOut update(CarIn dto) {
        return service.toOutDTO(service.update(
                dto.getId(),
                dto.getModelName(),
                dto.getCarName(),
                dto.getDescription(),
                dto.getDriverId()
        ));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        service.deleteById(id);
    }
}
