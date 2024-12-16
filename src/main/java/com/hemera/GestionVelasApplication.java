package com.hemera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hemera")
public class GestionVelasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionVelasApplication.class, args);
	}

}
