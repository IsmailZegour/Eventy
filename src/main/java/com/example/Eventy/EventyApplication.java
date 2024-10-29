package com.example.Eventy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.Eventy.Repository")
public class EventyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventyApplication.class, args);
	}

}
