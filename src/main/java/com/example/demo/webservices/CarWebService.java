package com.example.demo.webservices;

import com.example.demo.dto.CarDTO;
import com.example.demo.services.CarService;
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
public class CarWebService {

    private final CarService service;

    public List<CarDTO> getById(Optional<Integer> id) {
        return id
                .map(service::getById)
                .map(service::toDTO)
                .map(Collections::singletonList)
                .orElseGet(() -> service.getAll().stream()
                        .map(service::toDTO)
                        .collect(Collectors.toList()));
    }

    @Transactional
    public CarDTO create(CarDTO dto) {
        return service.toDTO(service.create(
                dto.getModelName(),
                dto.getCarName(),
                dto.getDescription(),
                dto.getDriverId()
        ));
    }

    @Transactional
    public CarDTO update(CarDTO dto) {
        return service.toDTO(service.update(
                dto.getId(),
                dto.getModelName(),
                dto.getCarName(),
                dto.getDescription(),
                dto.getDriverId()
        ));
    }

    @Transactional
    public void delete(Integer id) {
        service.deleteById(id);
    }
}
