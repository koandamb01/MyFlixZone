package com.David.javaProject.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.David.javaProject.models.Response;
import com.David.javaProject.models.general.User;
import com.David.javaProject.models.music.FavoriteService;
import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.services.AddressService;
import com.David.javaProject.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*") //this lets Angular access this server
public class UserController extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;
	
	@Autowired
	private FavoriteService favService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private AddressService addressService;
	
	// Register a new user
	@PostMapping("new")
	public Response createUser(@Valid @RequestBody User user, Errors errors, HttpSession session){
		Response response = new Response();
		
		if (errors.hasErrors()) {
			response.setStatus(false);
			response.setMessage("Validation errors");
			response.setData(errors.getAllErrors());
			return response;
		}
		else {
			User newUser = this.userService.registerUser(user);
			session.setAttribute("userId", newUser.getId());
			this.sessionService.setUserId(newUser.getId());
			
			newUser.getAddresses().clear();
			newUser.setCreatedAt(null);
			newUser.setUpdatedAt(null);
			newUser.setPassword(null);
			
			List<User> list = new ArrayList<>();
			list.add(newUser);
			
			response.setStatus(true);
			response.setMessage("You have successfully Register!");
			response.setData(list);
			return response;
		}
	}
	
	@PostMapping("login")
	public Response login(@RequestBody User logUser, HttpSession session) {
		Response res = new Response();
		
		if(!this.userService.authenticateUser(logUser.getEmail(), logUser.getPassword())) {
			res.setStatus(false);
			res.setMessage("Invalid email or password!");
		}
		else {
			User u = this.userService.findByEmail(logUser.getEmail());
			session.setAttribute("userId", u.getId());
			
			this.sessionService.setUserId(u.getId());
			
			System.out.println("Testing session: " + u.getId());
			
			u.getAddresses().clear();
			u.setCreatedAt(null);
			u.setUpdatedAt(null);
			u.setPassword(null);
			
			List<User> list = new ArrayList<>();
			list.add(u);
			
			res.setStatus(true);
			res.setMessage("You have successfully Login!");
			res.setData(list);
		}
		
		return res;
	}
	
	// logout
	@GetMapping("logout")
	public Response logout() {
		this.sessionService.clearSession();
		Response res = new Response(true, "You have successfully logout!");
		return res;
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
	
	
	// get user profile
	@GetMapping("profile")
	public Response userProfile(HttpSession session) {
		Response res = new Response();
		
//		Long userId = (Long) session.getAttribute("userId"); 
		
		Long userId = this.sessionService.getUserId();
	
		// check if the user is in session
		if(userId == null) {
			res.setStatus(false);
			res.setMessage("You must be logged In!");
		}
		else {
			// get the user object by using the session id
			User user = this.userService.findUserById(userId);
	
			user.getAddresses().clear();
			user.setCreatedAt(null);
			user.setUpdatedAt(null);
			user.setPassword(null);
			
			res.setStatus(true);
			res.setMessage("Request Completed!");
			
			List<User> list = new ArrayList<>();
			
			list.add(user);
			res.setData(list);
		}
		return res;
	}
	
	
	// update personal information
	@PutMapping("updatePersonal")
	public Response updatePersonal(@RequestBody User user) {
		Response res = new Response();
		
		Long userId = this.sessionService.getUserId();

		// get the user object by using the session id
		User oldUser = this.userService.findUserById(userId);
		
		// updated the user info
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		oldUser.setEmail(user.getEmail());
		
		oldUser = (User) this.userService.updatedPersonal(oldUser);
		
		oldUser.getAddresses();
		oldUser.setCreatedAt(null);
		oldUser.setUpdatedAt(null);
		oldUser.setPassword(null);
		
		res.setStatus(true);
		res.setMessage("Your Personal Information has been successfully Updated!");
		
		List<User> list = new ArrayList<>();
		
		list.add(oldUser);
		res.setData(list);
		
		return res;
	}
	
	
	// update password information
	@PutMapping("updatePassword")
	public Response updatePassword(@RequestBody User user) {
		Response res = new Response();
		
		User oldUser = this.userService.updatedPassword(user);
		
		if(oldUser == null) {
			res.setStatus(false);
			res.setMessage("Old Password did not match!");
		}
		else {
			oldUser.setCreatedAt(null);
			oldUser.setUpdatedAt(null);
			oldUser.setPassword(null);
			
			List<User> list = new ArrayList<>();
			list.add(oldUser);
			
			res.setStatus(true);
			res.setMessage("Your Password Information has been successfully Updated!");
			res.setData(list);
		}
		return res;
	}
	
	// created a new address
	@PostMapping("newAddress")
	public Response createAddress(@RequestBody Address address, Errors errors) {
		Response res = new Response();
		
		if(errors.hasErrors()) {
			res.setStatus(false);
			res.setMessage("Validation errors");
			res.setData(errors.getAllErrors());
			return res;
		}
		
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
