package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {
		System.out.println(1);
		System.out.println(2);
		System.out.println(3);
		SpringApplication.run(DemoApplication.class, args);
	}

}
