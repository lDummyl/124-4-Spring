package com.example.demo.services;


import com.example.demo.dataBases.entity.CarEntity;
import com.example.demo.dataBases.entity.UserEntity;
import com.example.demo.dataBases.repositories.CarRepository;
import com.example.demo.dataBases.repositories.UserRepo;
//import com.example.demo.db.repo.UserRepo;
import com.example.demo.dto.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final CarRepository carRepository;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public UserEntity init(Long userId, String userName, Integer yearOfBirth, Long carId) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userId);
            userEntity.setSuperName(userName);
            userEntity.setYearOfBirth(yearOfBirth);

            CarEntity carEntity = carRepository.findById(carId).orElse(null);
            userEntity.setCarEntityList(Arrays.asList(carEntity));

//            userRepo.save(userEntity);
//        List<UserEntity> all = userRepo.findAll();
//        for (UserEntity entity : all) {
//            log.info(objectMapper.writeValueAsString(entity));
//        }
            return userRepo.save(userEntity);
        }


        public UserEntity create(UserDetails userDetails) {
            return init(userDetails.getId(), userDetails.getName(), userDetails.getYearOfBirth(), userDetails.getCarId());
        }

        public List<UserEntity> getAll() {
            return userRepo.findAll();
        }

        public void remove(Long id) {
                userRepo.deleteById(id);
            }

        public UserEntity getUpdate(Long userId, String userName, Integer yearOfBirth) {

        UserEntity userEntity = userRepo.findById(userId).orElse(new UserEntity());
        userEntity.setSuperName(userName);
        userEntity.setYearOfBirth(yearOfBirth);
        return userRepo.save(userEntity);}

        public void getUpdate(Long id, UserDetails userDetails) {
        if (userRepo.findById(id).isEmpty()) {
            create(userDetails);
        }
        else {
            getUpdate(userDetails.getId(), userDetails.getName(), userDetails.getYearOfBirth());
        }
    }
        public Optional<UserEntity> getUser(Long id) {
        return userRepo.findById(id);
    }
}

