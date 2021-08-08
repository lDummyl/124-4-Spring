package com.example.demo.webservices.impl;

import com.example.demo.dto.in.DriverIn;
import com.example.demo.dto.out.DriverOut;
import com.example.demo.services.DriverService;
import com.example.demo.webservices.DriverWebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DriverWebServiceImpl implements DriverWebService {

    private final DriverService service;

    @Override
    public DriverOut getById(Integer id) {
        return service.toOutDTO(service.getById(id));
    }

    @Override
    public List<DriverOut> getAll() {
        return service.getAll().stream()
                .map(service::toOutDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public DriverOut create(DriverIn dto) {
        return service.toOutDTO(service.create(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getAge()
        ));
    }

    @Transactional
    @Override
    public DriverOut update(DriverIn dto) {
        return service.toOutDTO(service.update(
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
