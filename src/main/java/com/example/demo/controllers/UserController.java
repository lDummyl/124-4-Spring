package com.example.demo.controllers;

import com.example.demo.dataBases.entity.UserEntity;
//import com.example.demo.dataBases.entity.UserEntity;
import com.example.demo.dto.UserDetails;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

   private final UserService userService;
   PageRequest pageRequest;

   @GetMapping("/id/{id}")
   public Optional<UserEntity> getUser(@PathVariable Long id){
       return userService.getUser(id);
   }

    @GetMapping("/userName/{name}")
    public List<UserEntity> findByName(@PathVariable Optional<String> name,
                                       @PathVariable Optional<String> page){
        return userService.findByName(name.orElse(""));
    }


    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUser(){
        return userService.getAll();
    }

    @PostMapping("/addUser")
    public String addingUser(@RequestBody UserDetails userDetails){
       userService.create(userDetails);
       return "Ok";
   }

   @DeleteMapping("/delete/{id}")
    public String deletingUser(@PathVariable Long id){
        userService.remove(id);
        return "ok";
   }

   @PutMapping("/updateUser/{id}")
    public String updatingUser(@PathVariable Long id, @RequestBody UserDetails userDetails){
        userService.getUpdate(id, userDetails);
        return "ok";
   }
}
