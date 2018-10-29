package com.David.javaProject.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.David.javaProject.models.shopping.Order;
import com.David.javaProject.models.shopping.OrderProduct;
import com.David.javaProject.models.shopping.OrderProductRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.Response;
import com.David.javaProject.models.general.User;
import com.David.javaProject.services.UserService;


@RestController
@RequestMapping("/users")
//this lets Angular access this server
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class UserController {
	@Autowired
	private UserService userService;
	
	// Register a new user

	@PostMapping("/new")
	public Response createUser(@Valid @RequestBody User user, Errors errors) {
		Response response = new Response();

		if (errors.hasErrors()) {
			response.setStatus(false);
			response.setMessage("Validation errors");
			response.setData(errors.getAllErrors());
			return response;
		}
		else {
			User newUser = this.userService.registerUser(user);
			List<User> list = new ArrayList<>();
			list.add(newUser);
			
			response.setStatus(true);
			response.setMessage("You have successfully Register!");
			response.setData(list);
			return response;
		}
	}

	
	// get all the users
	@GetMapping("")
	public Response getUsers(){
		Response res = new Response(true, "Request Completed");
		List<User> users = this.userService.findAllUsers();
		res.setData(users);
		return res;
	}
	
	// get a user by Id
	@GetMapping("{id}")
	public Response getUser(@PathVariable Long id){
		Response res = new Response(true, "Request Completed");
		
		List<User> list = new ArrayList<>();
		list.add(this.userService.findUserById(id));
		res.setData(list);
		
		return res;
	}
	
//	@DeleteMapping("/user/{id}")
//	public boolean deleUser(@PathVariable Long id) {
//		userRepository.deleteById(id);
//		return true;
//	}
	
//	@PutMapping("/user")
//	public User updUser(@RequestBody User user) {
//		return userRepository.save(user);
//	}
	
//	@PostMapping("/user")
//	public User creUser(@RequestBody User user) {
//		return userRepository.save(user);
//	}
	
//	@GetMapping("/cities")
//	public List<City> getCities(){
//		return cityRepository.findAll();
//	}
	
//	@GetMapping("/shipping/{id}")
//	public Response shipping(@PathVariable Long id){
//
//		Optional<User> u = userRepository.findById(id);
//
//		if(u.isPresent()) {
//			User user = u.get();
////			List<User> list = new ArrayList<>();
////			list.add(user);
//			Response res = new Response(true, "You did it?", user.getAddresses());
//			return res;
//		} else {
//			Response res = new Response(false, "Could not find user by ID");
//
//			return res;
//		}
//
//
//	}


//	@PostMapping("/newUser")
//	public BindingResult createUser(@RequestBody User user) {
//		userRepository.save(user);
//		return result;
//	}

}
