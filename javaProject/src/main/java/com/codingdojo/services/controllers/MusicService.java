package com.codingdojo.services.controllers;

import org.springframework.stereotype.Service;

import com.codingdojo.models.general.RoleRepo;
import com.codingdojo.models.general.SubscriptionRepo;
import com.codingdojo.models.general.UserRepo;
import com.codingdojo.models.music.FavoriteRepo;

@Service
public class MusicService {
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final SubscriptionRepo subscriptionRepo;
	private final FavoriteRepo faveriteRepo;
	
	public MusicService(FavoriteRepo faveriteRepo, UserRepo userRepo, RoleRepo roleRepo, SubscriptionRepo subscriptionRepo) {
		this.faveriteRepo = faveriteRepo;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.subscriptionRepo = subscriptionRepo;
	}
}
