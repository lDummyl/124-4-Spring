package com.example.demo.controllers;

import com.example.demo.dto.DriverDTO;
import com.example.demo.dto.OutDriverDTO;
import com.example.demo.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver/")
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/all")
    public ResponseEntity<List<DriverDTO>> findAll(){
        return new ResponseEntity<>(driverService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> findById(@PathVariable Integer id){
        return new ResponseEntity<>(driverService.findById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<OutDriverDTO> save(@RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.save(driverDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OutDriverDTO> update(@RequestBody DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.update(driverDTO), HttpStatus.OK);
    }

    @PostMapping("/newList")
    public ResponseEntity<List<OutDriverDTO>> saveAll(@RequestBody List<DriverDTO> list) {
        return new ResponseEntity<>(driverService.saveAll(list), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        driverService.delete(id);
        return new ResponseEntity<>("Driver with id: " + id + " deleted.", HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public Page<DriverDTO> getPage(@RequestParam(name = "number") int number,
                                   @RequestParam(name = "size") int size,
                                   @RequestParam(name = "sortby", required = false, defaultValue = "id") String sortBy){
        return driverService.getPage(number, size, sortBy);
    }

    @GetMapping("/age")
    public Page<DriverDTO> getPageDriversAfterAge(@RequestParam(name = "number") int number,
                                                  @RequestParam(name = "size") int size,
                                                  @RequestParam(name = "age") int age){
        return driverService.getPageDriversAfterAge(number, size, age);
    }

    @GetMapping("/setCar")
    public ResponseEntity<DriverDTO> setCar(@RequestParam(name = "driverid") int driverid,
                                            @RequestParam(name = "carid") int carid){
        return new ResponseEntity<>(driverService.setCar(driverid, carid), HttpStatus.OK);
    }

    @GetMapping("/removeCar")
    public ResponseEntity<DriverDTO> removeCar(@RequestParam(name = "driverid") int driverid,
                                               @RequestParam(name = "carid") int carid){
        return new ResponseEntity<>(driverService.removeCar(driverid, carid), HttpStatus.OK);
    }
}
