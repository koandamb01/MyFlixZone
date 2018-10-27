package com.David.javaProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.David.javaProject.models.User;
import com.David.javaProject.repositories.UserRepository;

@SpringBootApplication
//CommandLineRunner runs whenever server starts
public class JavaProjectApplication implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}

	
	//This runs whenever server starts
	@Override
	public void run(String... args) throws Exception {
//		userRepository.save(new User("Bob","Bobbers"));
//		userRepository.save(new User("Bamboozled","ByMichael"));
//		userRepository.save(new User("French","Fries"));
	}
}
