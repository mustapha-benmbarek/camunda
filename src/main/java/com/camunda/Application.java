package com.camunda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

	/*
	 * Entry point for the Animal API application. 
	 * Bootstraps the Spring Boot context and logs the active environment profile.
	 */
	public static void main(String[] args) {
		log.info("Loading the application ...");
		SpringApplication.run(Application.class, args);
	}
}