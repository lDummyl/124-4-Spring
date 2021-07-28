package com.example.demo.webservices;

import com.example.demo.dto.DriverDTO;
import com.example.demo.services.DriverService;
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
public class DriverWebService {

    private final DriverService service;

    public List<DriverDTO> getById(Optional<Integer> id) {
        return id
                .map(integer -> Collections.singletonList(service.toDTO(service.getById(integer))))
                .orElseGet(() -> service.getAll().stream()
                        .map(service::toDTO)
                        .collect(Collectors.toList()));
    }

    public DriverDTO create(DriverDTO dto) {
        return service.toDTO(service.create(dto));
    }

    public DriverDTO update(DriverDTO dto) {
        return service.toDTO(service.updateById(dto));
    }

    public void delete(Integer id) {
        service.deleteById(id);
    }
}
