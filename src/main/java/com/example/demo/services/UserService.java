package com.example.demo.services;


import com.example.demo.db.ent.CarEntity;
import com.example.demo.db.ent.UserEntity;
import com.example.demo.db.repo.UserRepo;
import com.example.demo.dto.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;


    private final ObjectMapper objectMapper;

    @SneakyThrows
    @PostConstruct
    public void init(){
        UserEntity userEntity = new UserEntity();
        CarEntity carEntity = new CarEntity();
        carEntity.setModel("Lada");
        userEntity.setCarEntityList(Arrays.asList(carEntity));
        userRepo.save(userEntity);
        List<UserEntity> all = userRepo.findAll();
        for (UserEntity entity : all) {
            log.info(objectMapper.writeValueAsString(entity));
        }
        List<UserEntity> masha = userRepo.findBySuperName("Masha");
        System.out.println(masha.size());
    }


    public List<UserDetails> getAll() {
        List<UserEntity> all = userRepo.findAll();
        throw new RuntimeException("Not impl");

    }

    public void add(UserDetails userDetails) {
        throw new RuntimeException("Not impl");
    }
}
