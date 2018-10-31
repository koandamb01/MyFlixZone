package com.David.javaProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.Response;
import com.David.javaProject.models.general.User;
import com.David.javaProject.models.music.Favorite;
import com.David.javaProject.models.music.FavoriteService;
import com.David.javaProject.services.UserService;

@RestController
@RequestMapping("/music")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")

public class MusicController extends UserController {
    @Autowired
    private FavoriteService favService;
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionService sessionService;
    
	@GetMapping("/likes/{musicId}")
	public Response likeMusic(@PathVariable("musicId") Long musicId) {
		Response res = new Response();
		
		// get the user id from the session service
		Long userId = this.sessionService.getUserId();
		
		// check if user is logged in
		if(userId == null) {
			res.setStatus(false);
			res.setMessage("You must be logged in!");
		}
		else {
			
			User user = this.userService.findUserById(userId);
			
			// like the song now
			this.favService.favoriteAmusic(user, musicId);
			res.setStatus(true);
			res.setMessage("Music liked!");
			
		}
		return res;
	}
	
	
	@GetMapping("/unlikes/{musicId}")
	public Response unlikeMusic(@PathVariable("musicId") Long musicId) {
		Response res = new Response();
		
		// get the user id from the session service
		Long userId = this.sessionService.getUserId();
		
		// check if user is logged in
		if(userId == null) {
			res.setStatus(false);
			res.setMessage("You must be logged in!");
		}
		else {
			
			User user = this.userService.findUserById(userId);
			
			// unlike the song now
			this.favService.unfavoriteAmusic(user, musicId);
			res.setStatus(true);
			res.setMessage("Music unliked!");
			
		}
		return res;
	}
	
	
	
	@GetMapping("/alllikes")
	public Response allLikes() {
		Response res = new Response();
		
		// get the user id from the session service
		Long userId = this.sessionService.getUserId();
		
		// check if user is logged in
		if(userId == null) {
			res.setStatus(false);
			res.setMessage("You must be logged in!");
		}
		else {
			
			// get the user by email
			User user = this.userService.findUserById(userId);
			
			res.setStatus(true);
			res.setMessage("Success");
			res.setData(user.getFavorites()); 
			
		}
		return res;
	}

}
