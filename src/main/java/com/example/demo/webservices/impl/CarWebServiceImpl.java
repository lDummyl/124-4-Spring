package com.example.demo.webservices.impl;

import com.example.demo.dto.in.CarIn;
import com.example.demo.dto.out.CarOut;
import com.example.demo.services.impl.CarServiceImpl;
import com.example.demo.webservices.CarWebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarWebServiceImpl implements CarWebService {

    private final CarServiceImpl service;

    @Override
    public List<CarOut> getById(Optional<Integer> id) {
        return id
                .map(service::getById)
                .map(service::toOutDTO)
                .map(Collections::singletonList)
                .orElseGet(() -> service.getAll().stream()
                        .map(service::toOutDTO)
                        .collect(Collectors.toList()));
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
