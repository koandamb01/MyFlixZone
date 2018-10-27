package com.David.javaProject.controllers;

import com.David.javaProject.models.general.RoleRepo;
import com.David.javaProject.models.general.SubscriptionRepo;
import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paypal")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class ShoppingController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private SubscriptionRepo subscriptionRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private ProductRepo productRepo;
}
