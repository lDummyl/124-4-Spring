package com.example.demo.webservices.impl;

import com.example.demo.dto.DriverDTO;
import com.example.demo.services.impl.DriverServiceImpl;
import com.example.demo.webservices.DriverWebService;
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
public class DriverWebServiceImpl implements DriverWebService {

    private final DriverServiceImpl service;

    @Override
    public List<DriverDTO> getById(Optional<Integer> id) {
        return id
                .map(service::getById)
                .map(service::toDTO)
                .map(Collections::singletonList)
                .orElseGet(() -> service.getAll().stream()
                        .map(service::toDTO)
                        .collect(Collectors.toList()));
    }

    @Transactional
    @Override
    public DriverDTO create(DriverDTO dto) {
        return service.toDTO(service.create(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge()
        ));
    }

    @Transactional
    @Override
    public DriverDTO update(DriverDTO dto) {
        return service.toDTO(service.update(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge()
        ));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        service.deleteById(id);
    }
}
