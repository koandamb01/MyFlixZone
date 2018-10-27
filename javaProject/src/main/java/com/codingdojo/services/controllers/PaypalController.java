package com.codingdojo.services.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaypalController {
	private final PaypalService paypalService;
	
	public PaypalController(PaypalService paypalService) {
		this.paypalService = paypalService;
	}
}
 