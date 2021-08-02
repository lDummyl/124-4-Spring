package com.example.demo.webServices;

import com.example.demo.DTO.DriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.servises.DriverService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DriverWebService {
    private final DriverService service;

    public DriverWebService(DriverService service) {
        this.service = service;
    }

    public List<DriverDTO> getByID(Optional<Integer> id){
        return id
                .map(service::getById)
                .map(service::toDTO)
                .map(Collections::singletonList)
                .orElseGet(() -> service.getAll().stream()
                        .map(service::toDTO)
                        .collect(Collectors.toList()));
    }
//
//    Аналог стримов
//    public List<DriverDTO> getById(Long id){
//        List<DriverDTO> DTOList = new ArrayList<>();
//
//        if(service.getById(id) != null) {
//            Driver driver = service.getById(id);
//            DriverDTO driverDTO = service.toDTO(driver);
//            DTOList.add(driverDTO);
//        }
//        else {
//            List<Driver> drivers = service.getAll();
//
//            for (Driver driver : drivers
//                 ) {
//                DTOList.add(service.toDTO(driver));
//            }
//        }
//        return DTOList;
//    }
    @Transactional
    public DriverDTO create(DriverDTO driverDTO){
        return service.toDTO(service.create(
                driverDTO.getFirstname(),
                driverDTO.getSurname(),
                driverDTO.getAge()
        ));
    }

    @Transactional
    public DriverDTO update(DriverDTO driverDTO){
            return service.toDTO(service.update(
                    driverDTO.getId(),
                    driverDTO.getFirstname(),
                    driverDTO.getSurname(),
                    driverDTO.getAge()
            ));
    }

    @Transactional
    public void delete(Integer id){
        service.deleteById(id);
    }
}
