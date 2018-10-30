package com.David.javaProject.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.Response;
import com.David.javaProject.models.general.User;
import com.David.javaProject.models.music.FavoriteService;
import com.David.javaProject.services.UserService;

@RestController
@RequestMapping("/music")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")

public class MusicController {
    @Autowired
    private FavoriteService favService;
    
	@Autowired
	private UserService userService;
    
	@GetMapping("/likes/{musicId}")
	public Response likeMusic(@PathVariable("musicId") Long musicId, HttpSession session) {
		Response res = new Response();
		
		// check if user is logged in
		if(session.getAttribute("userId") == null) {
			res.setStatus(false);
			res.setMessage("You must be logged in!");
		}
		else {
			Long userId = (Long) session.getAttribute("userId"); 
			User user = this.userService.findUserById(userId);
			
			// like the song now
			this.favService.favoriteAmusic(user, musicId);
			res.setStatus(true);
			res.setMessage("Music liked!");
			
		}
		return res;
	}

}
