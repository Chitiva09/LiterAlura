package com.alura.literAlura.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.alura.literAlura")
@EnableJpaRepositories(basePackages = "com.alura.literAlura.repository")
@EntityScan(basePackages = "com.alura.literAlura.entity")
public class LiterAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

}
