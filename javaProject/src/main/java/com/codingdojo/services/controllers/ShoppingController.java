package com.codingdojo.services.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingController {
	private final ShoppingService shoppingService;

	public ShoppingController(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}
}
