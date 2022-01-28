package com.example.demo.services;


import com.example.demo.web.MyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnotatedBean {


    private final MyClient myClient;

    @Scheduled(initialDelay = 1L, fixedRate = 5000L)
    public void clicker(){
        System.out.println("myClient.getHello() = " + myClient.getHello());
    }

}
