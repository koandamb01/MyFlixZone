package com.David.javaProject.controllers;

import org.springframework.stereotype.Service;

@Service
public class SessionService {
	private Long userId;

	public SessionService() {}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
