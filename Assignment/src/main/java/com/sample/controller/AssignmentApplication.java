package com.sample.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sample.service.IndiaPhoneService;

@SpringBootApplication
public class AssignmentApplication {
	
	@Bean
    public IndiaPhoneService indiaPhoneService() {
        return new IndiaPhoneService();
    }

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
