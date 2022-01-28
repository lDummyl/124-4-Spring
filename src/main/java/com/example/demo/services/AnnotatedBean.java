package com.example.demo.services;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AnnotatedBean {


    @Scheduled(initialDelay = 1L, fixedRate = 5000L)
    public void clicker(){
        System.out.println(" Click! ");
    }

}
