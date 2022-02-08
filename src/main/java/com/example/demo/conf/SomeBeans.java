package com.example.demo.conf;


import com.example.demo.services.SuperService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Random;

@Configuration
@EnableScheduling
public class SomeBeans {

    @Bean
    public SuperServiceInt lalala() {
        return new Random().nextBoolean() ? new SuperService() : new SuperServiceInt() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

}
