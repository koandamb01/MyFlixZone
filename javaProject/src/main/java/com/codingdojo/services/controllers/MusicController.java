package com.codingdojo.services.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicController {
	private final MusicService musicService;
	
	public MusicController(MusicService musicService) {
		this.musicService = musicService;
	}
}
