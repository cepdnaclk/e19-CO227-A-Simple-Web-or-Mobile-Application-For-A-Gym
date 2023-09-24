package com.e19co227.gymhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GymhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymhubApplication.class, args);
	}

}

