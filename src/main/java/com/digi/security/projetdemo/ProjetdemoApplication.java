package com.digi.security.projetdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProjetdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetdemoApplication.class, args);
	}

}
