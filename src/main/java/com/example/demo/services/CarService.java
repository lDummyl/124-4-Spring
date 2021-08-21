package com.example.demo.services;
import com.example.demo.config.Config;
import com.example.demo.dataBases.entity.CarEntity;
import com.example.demo.dataBases.repositories.CarRepository;
import com.example.demo.dto.CarDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final Config config;


    public void create(CarDetails carDetails) {
        config.carLimit();
        init(carDetails.getModel(), carDetails.getId());
    }


    public void init(String model, Long carId) {
        CarEntity carEntity = new CarEntity();
        carEntity.setModel(model);
        carEntity.setId(carId);

        carRepository.save(carEntity);

    }

    public Optional<CarEntity> getCar(Long id) {
        return carRepository.findById(id);

    }


    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    public void deletingCar(Long id) {
        carRepository.deleteById(id);
    }

    public CarEntity updatingCar(Long id, String model) {
        CarEntity carEntity = carRepository.findById(id).orElseThrow(()-> new RuntimeException("No car to modify"));
        carEntity.setId(id);
        carEntity.setModel(model);
        return carRepository.save(carEntity);
    }


    public void updatingCar(Long id, CarDetails carDetails) {
        if (carRepository.findById(id).isEmpty()) {
            create(carDetails);
        } else {
            updatingCar(carDetails.getId(), carDetails.getModel());
        }
    }

    public List<CarEntity> getByModel(String model) {

        return carRepository.findByModel(model);
    }
}
