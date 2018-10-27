package com.David.javaProject.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paypal")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class PaypalController {
	private final PaypalService paypalService;
	
	public PaypalController(PaypalService paypalService) {
		this.paypalService = paypalService;
	}
}
 