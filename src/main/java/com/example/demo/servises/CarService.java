package com.example.demo.servises;

import com.example.demo.DTO.CarDTO;
import com.example.demo.entities.Car;
import com.example.demo.entities.Driver;
import com.example.demo.exeptions.CommonAppExeption;
import com.example.demo.repositories.CarRepository;
import com.example.demo.repositories.DriverRepository;
import com.sun.istack.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository repository;
    private final DriverRepository driverRepository;

    private static final String NO_CAR_MESSAGE = "There is no such car with id ";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null!";

    public CarService(CarRepository repository, DriverRepository driverRepository) {
        this.repository = repository;
        this.driverRepository = driverRepository;
    }

    @Transactional
    public Car create(String brand, String model, String description, Long DriverID){
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setDescription(description);
        car.setDriver(driverRepository.findById(DriverID).orElse(null));
        return repository.save(car);
    }

    public Car getByID(Long CarID){
        Optional<Car> optionalCar = repository.findById(CarID);
        if (optionalCar.isPresent()) {
            return optionalCar.get();
        }
        else throw new CommonAppExeption(NO_CAR_MESSAGE + CarID);
    }

    public List<Car> getAll(){
        return repository.findAll();
    }

    @Transactional
    public Car update(Long id, String brand, String model, String description, Long DriverID){
        Car car = repository.findById(id).orElseThrow(() -> new CommonAppExeption(NO_CAR_MESSAGE + id));
        car.setBrand(brand);
        car.setModel(model);
        car.setDescription(description);
        Driver driver = driverRepository.findById(DriverID).orElse(null);
        car.setDriver(driver);
        return  repository.save(car);
    }

    @Transactional
    public void deleteByID(Long id){
        if (!repository.existsById(id)){
            throw new CommonAppExeption(NO_CAR_MESSAGE + id);
        }
        repository.deleteById(id);
    }

    public CarDTO toDTO(Car car){
        if (car == null) {
            throw new CommonAppExeption(DTO_MUST_NOT_BE_NULL_MESSAGE);
        }
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setDescription(car.getDescription());

        if (car.getDriver() != null) {
            dto.setDriverID(car.getDriver().getId());
        }
        else dto.setDriverID(null);
        return dto;
    }
}
