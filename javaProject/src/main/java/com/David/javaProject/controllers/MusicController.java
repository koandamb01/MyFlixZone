package com.David.javaProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.music.FavoriteRepo;
import com.David.javaProject.repositories.UserRepository;

@RestController
@RequestMapping("/music")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")

public class MusicController {
    @Autowired
	private UserRepository userRepo;
    @Autowired
	private FavoriteRepo faveriteRepo;

}
