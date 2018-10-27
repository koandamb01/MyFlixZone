package com.David.javaProject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.User;
import com.David.javaProject.repositories.UserRepository;

@RestController
@RequestMapping("/api")
//this lets Angular access this server
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id){
		Optional<User> u = userRepository.findById(id);

        if(u.isPresent()) {
            return u.get();
        } else {
            return null;
        }
	}
	
	@DeleteMapping("/user/{id}")
	public boolean deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return true;
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
}
