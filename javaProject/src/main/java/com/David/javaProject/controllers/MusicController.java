package com.David.javaProject.controllers;

import com.David.javaProject.models.general.RoleRepo;
import com.David.javaProject.models.general.SubscriptionRepo;
import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.music.FavoriteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")

public class MusicController {
    @Autowired
	private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
	private SubscriptionRepo subscriptionRepo;
    @Autowired
	private FavoriteRepo faveriteRepo;

}
