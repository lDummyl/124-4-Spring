package com.example.demo.controllers;

import com.example.demo.dto.UserDetails;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/users")
//public class MyController {
//
//    private final UserService userService;
//
//
//    @GetMapping("/{id}")
//    public String getSomeData(@PathVariable Integer id) {
//        return "Hello # " + id;
//    }
//
//    @GetMapping("/all")
//    public List<UserDetails> getAll() {
//        return userService.getAll();
//    }
//
//    @PostMapping("/register")
//    public String getSomeOtherData(@RequestBody UserDetails userDetails) {
//        log.info(userDetails.toString());
//        userService.add(userDetails);
//        return "Ok!";
//    }
//}
