package com.example.demo.controllers;

import com.example.demo.dataBases.entity.UserEntity;
//import com.example.demo.dataBases.entity.UserEntity;
import com.example.demo.dto.UserDetails;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

   private final UserService userService;

   @GetMapping("/{id}")
   public Optional<UserEntity> getUser(@PathVariable Long id){
       return userService.getUser(id);
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
