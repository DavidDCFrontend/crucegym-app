package com.crucegym.crucegym_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.crucegym")
public class CrucegymAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrucegymAppApplication.class, args);
	}

}
