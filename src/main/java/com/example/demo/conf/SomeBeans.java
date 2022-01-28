package com.example.demo.conf;


import com.example.demo.services.SuperService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SomeBeans {

    @Bean
    public SuperService lalala() {
        return new SuperService();
    }

}
