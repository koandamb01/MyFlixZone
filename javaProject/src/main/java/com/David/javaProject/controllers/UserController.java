package com.David.javaProject.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.paypal.CityRepo;
import com.David.javaProject.models.paypal.ShippingAddressRepo;


@RestController
@RequestMapping("/users")
//this lets Angular access this server
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class UserController {
	@Autowired
	private UserRepo userRepository;
	@Autowired
	private CityRepo cityRepository;
	@Autowired
	private ShippingAddressRepo shippingAddressRepo;
	
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
		
		return response;
	}

	
	// get all the users
	@GetMapping("")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	// get a user by Id
	@GetMapping("{id}")
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
//			Response res = new Response(true, "You did it?", user.getShippingAddresses());
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
