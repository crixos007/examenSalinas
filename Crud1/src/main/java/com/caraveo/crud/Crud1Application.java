package com.caraveo.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages = {"com.caraveo.crud"})
@EnableCaching
@Configuration
@EnableAsync
@SpringBootApplication
public class Crud1Application {

	public static void main(String[] args) {
		SpringApplication.run(Crud1Application.class, args);
	}

}
