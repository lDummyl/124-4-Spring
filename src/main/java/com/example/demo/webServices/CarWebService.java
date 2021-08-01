package com.example.demo.webServices;

import com.example.demo.DTO.CarDTO;
import com.example.demo.servises.CarService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarWebService {
    private final CarService service;

    public CarWebService(CarService service) {
        this.service = service;
    }

    public List<CarDTO> getByID(Optional<Long> id){
        return id
                .map(service::getByID)
                .map(service::toDTO)
                .map(Collections::singletonList)
                .orElseGet(() -> service.getAll().stream()
                            .map(service::toDTO)
                            .collect(Collectors.toList()));
    }

    @Transactional
    public CarDTO create(CarDTO carDTO){
        return service.toDTO(service.create(
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getDescription(),
                carDTO.getDriverID()
        ));
    }

    @Transactional
    public CarDTO update(CarDTO carDTO){
        return service.toDTO(service.update(
                carDTO.getId(),
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getDescription(),
                carDTO.getDriverID()
        ));
    }

    @Transactional
    public void delete(Long id){
        service.deleteByID(id);
    }
}
