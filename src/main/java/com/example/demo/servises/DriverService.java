package com.example.demo.servises;

import com.example.demo.DTO.DriverDTO;
import com.example.demo.entities.Driver;
import com.example.demo.exeptions.CommonAppExeption;
import com.example.demo.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository repository;

    private static final String NO_DRIVER_MESSAGE = "There is no such driver with id ";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    public DriverService(DriverRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Driver create(String firstname, String surname, int age){
        Driver driver = new Driver();
        driver.setName(firstname);
        driver.setSurname(surname);
        driver.setAge(age);
        return repository.save(driver);
    }

    public Driver getById(Long id) {
        Optional<Driver> optionalDriver = repository.findById(id);
        if (optionalDriver.isPresent()){
            return optionalDriver.get();
        }
        else {
            throw new CommonAppExeption(NO_DRIVER_MESSAGE + id);
        }
    }

    public List<Driver> getAll(){
        return repository.findAll();
    }

    @Transactional
    public Driver update(Long id, String firstname, String surname, int age){
        Driver driver = repository.findById(id).orElseThrow(() -> new CommonAppExeption(NO_DRIVER_MESSAGE + id));
        driver.setName(firstname);
        driver.setSurname(surname);
        driver.setAge(age);
        return repository.save(driver);
    }

    @Transactional
    public void deleteById(Long id){
        if (!repository.existsById(id)) {
            throw new CommonAppExeption(NO_DRIVER_MESSAGE + id);
        }
        else repository.deleteById(id);
    }

    public DriverDTO toDTO(Driver driver){
        if(driver == null) {
            throw new CommonAppExeption(DTO_MUST_NOT_BE_NULL_MESSAGE);
        }
    DriverDTO dto = new DriverDTO();
    dto.setAge(driver.getAge());
    dto.setFirstname(driver.getName());
    dto.setSurname(driver.getSurname());
    return dto;
    }
}
