package com.David.javaProject;

import com.David.javaProject.models.paypal.City;
import com.David.javaProject.models.paypal.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//CommandLineRunner runs whenever server starts
public class JavaProjectApplication implements CommandLineRunner{
	@Autowired
	private CityRepo cityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}

	
	//This runs whenever server starts
	@Override
	public void run(String... args) throws Exception {
//		cityRepository.save(new City("Seattle"));
//		cityRepository.save(new City("Chicago"));
//		cityRepository.save(new City("Paris"));
//		cityRepository.save(new City("London"));

	}
}
