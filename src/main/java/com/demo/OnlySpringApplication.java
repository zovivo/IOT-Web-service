package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableAsync
public class OnlySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlySpringApplication.class, args);
	}

}
