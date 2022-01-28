package com.example.demo;

import com.example.demo.services.SuperService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
		SuperService bean = applicationContext.getBean(SuperService.class);
		System.out.println("bean = " + bean);

	}

	@Bean
	public SuperService lalala() {
		return new SuperService();
	}



}
