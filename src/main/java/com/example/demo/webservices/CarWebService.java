package com.example.demo.webservices;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarWebService {

    private final CarService service;

    public List<CarDTO> getById(Optional<Integer> id) {
        return id
                .map(integer -> Collections.singletonList(service.toDTO(service.getById(integer))))
                .orElseGet(() -> service.getAll().stream()
                        .map(service::toDTO)
                        .collect(Collectors.toList()));
    }

    public CarDTO create(CarDTO dto) {
        return service.toDTO(service.create(dto));
    }

    public CarDTO update(CarDTO dto) {
        return service.toDTO(service.updateById(dto));
    }

    public void delete(Integer id) {
        service.deleteById(id);
    }
}
