package com.David.javaProject.controllers;

import org.springframework.stereotype.Service;

@Service
public class SessionService {
	private Long userId = null;

	public SessionService() {}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void clearSession() {
		this.userId = null;
	}
}
