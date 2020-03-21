package com.gastos.exes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ExesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExesApplication.class, args);
	}

}
