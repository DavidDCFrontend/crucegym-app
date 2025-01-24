package com.crucegym.crucegym_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.crucegym")
@EntityScan(basePackages = "com.crucegym")
@EnableJpaRepositories(basePackages = "com.crucegym.repositories")
public class CrucegymAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrucegymAppApplication.class, args);
	}
}
