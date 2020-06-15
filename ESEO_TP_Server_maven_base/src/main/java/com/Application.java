package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.config.ConfigRead;

@SpringBootApplication
public class Application {
	private static final String PASSWORD = ConfigRead.getString("PASSWORD");

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		System.out.println("Application demarree !");

	}
}
