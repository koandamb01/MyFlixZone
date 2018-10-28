package com.David.javaProject.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.David.javaProject.models.Response;
import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.paypal.City;
import com.David.javaProject.models.paypal.CityRepo;
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

import com.David.javaProject.models.general.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
//this lets Angular access this server
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class UserController {
	@Autowired
	private UserRepo userRepository;
	@Autowired
	private CityRepo cityRepository;

	@PostMapping("/newUser")
	public Response createUser(@RequestBody User user) {
		User yoink = userRepository.save(user);
		List <User> list = new ArrayList<>();
		list.add(yoink);
		Response res = new Response(true, "Meakjfelkaw", list);

		return res;
	}

//	@PostMapping("/newUser")
//	public BindingResult createUser(@RequestBody User user) {
//		userRepository.save(user);
//		return result;
//	}


	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}

	@GetMapping("/cities")
	public List<City> getCities(){
		return cityRepository.findAll();
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
	public boolean deleUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return true;
	}
	
	@PutMapping("/user")
	public User updUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/user")
	public User creUser(@RequestBody User user) {
		return userRepository.save(user);
	}
}
