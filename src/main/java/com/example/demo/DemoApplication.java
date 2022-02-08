package com.example.demo;

import com.example.demo.services.SuperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class DemoApplication {


    @Autowired
    private ObjectMapper objectMapper;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        SuperService bean = applicationContext.getBean(SuperService.class);
        System.out.println("bean = " + bean);


    }


}
