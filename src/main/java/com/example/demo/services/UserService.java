package com.example.demo.services;


import com.example.demo.db.ent.CarEntity;
import com.example.demo.db.ent.UserEntity;
import com.example.demo.db.repo.UserRepo;
import com.example.demo.dto.UserDetails;
import com.example.demo.ex.AwesomeServiceException;
import com.example.demo.ex.TypicalError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
public class UserService {

    private final UserRepo userRepo;
    private final ObjectMapper objectMapper;
    private final SuperService superService;


    @Autowired
    private AnnotatedBean annotatedBean;

    @Value("${age}")
    private Integer minDriverAge;

    public UserService(UserRepo userRepo,
                       ObjectMapper objectMapper,
                       SuperService superService) {
        this.userRepo = userRepo;
        this.objectMapper = objectMapper;
        this.superService = superService;
        System.out.println("minDriverAge = " + minDriverAge);
    }


    {
        System.out.println("minDriverAge = " + minDriverAge);
    }


    @SneakyThrows
    @PostConstruct
    public void init(){
        System.out.println("minDriverAge = " + minDriverAge);
        System.out.println("superService = " + superService);
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

    public UserDetails getUser(Integer id) {
        Optional<UserEntity> byId = userRepo.findById(id.longValue());
        UserEntity user = byId.orElseThrow(() -> new AwesomeServiceException("Ooops", TypicalError.NOT_FOUND));
        UserDetails userDetails = new UserDetails();
        userDetails.setId(user.getId().intValue());
        userDetails.setName(user.getSuperName());
        return userDetails;
    }
}
