package com.David.javaProject.controllers;


import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.OrderProductRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.ProductRepo;
import com.David.javaProject.repositories.UserRepository;

@RestController
@RequestMapping("/paypal")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class ShoppingController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderProductRepo orderProductRepo;


}
