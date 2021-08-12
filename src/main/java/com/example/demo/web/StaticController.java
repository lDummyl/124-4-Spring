package com.example.demo.web;

import com.example.demo.db.entities.CarEntity;
import com.example.demo.db.entities.DriverEntity;
import com.example.demo.db.repositories.CarRepository;
import com.example.demo.db.repositories.DriverRepository;
import com.example.demo.dto.out.CarOut;
import com.example.demo.dto.out.DriverOut;
import com.example.demo.webservices.CarWebService;
import com.example.demo.webservices.DriverWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class StaticController {

    private final DriverWebService driverService;
    private final CarWebService carService;

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/cars")
    public ModelAndView showCars() {
        List<CarOut> cars = carService.getAll();

        Map<String, Object> params = new HashMap<>();
        params.put("cars", cars);

        return new ModelAndView("showCars", params);
    }

    @GetMapping("/drivers")
    public ModelAndView showDrivers() {
        List<DriverOut> drivers = driverService.getAll();

        Map<String, Object> params = new HashMap<>();
        params.put("drivers", drivers);

        return new ModelAndView("showDrivers", params);
    }

    @GetMapping("/fillTestData")
    public ModelAndView fillTestData() {
        driverRepository.save(new DriverEntity(null, "Ivanov", "Ivan", 19));
        driverRepository.save(new DriverEntity(null, "Petrov", "Petr", 25));
        driverRepository.save(new DriverEntity(null, "Sidorov", "Sidor", 36));
        driverRepository.save(new DriverEntity(null, "Vasisualiy", "Pupkin", 49));

        carRepository.save(new CarEntity(null, "2101", "Копейка", "", null));
        carRepository.save(new CarEntity(null, "2104", "Четверка", "", null));
        carRepository.save(new CarEntity(null, "2106", "Шестерка", "", null));
        carRepository.save(new CarEntity(null, "Diablo", "Lamborgini", "", null));

        return new ModelAndView("fillTestData", new HashMap<>());
    }
}