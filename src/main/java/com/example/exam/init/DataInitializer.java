package com.example.exam.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.exam.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		userService.init();
		System.out.println("User already initialized!");
		return;
	}
}
