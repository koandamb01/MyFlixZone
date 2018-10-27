package com.codingdojo.services.controllers;

import org.springframework.stereotype.Service;

import com.codingdojo.models.general.RoleRepo;
import com.codingdojo.models.general.SubscriptionRepo;
import com.codingdojo.models.general.UserRepo;
import com.codingdojo.models.shopping.CategoryRepo;
import com.codingdojo.models.shopping.OrderRepo;
import com.codingdojo.models.shopping.ProductRepo;

@Service
public class ShoppingService {
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final SubscriptionRepo subscriptionRepo;
	private final CategoryRepo categoryRepo;
	private final OrderRepo orderRepo;
	private final ProductRepo productRepo;

	public ShoppingService(
			UserRepo userRepo,
			RoleRepo roleRepo,
			SubscriptionRepo subscriptionRepo,
			CategoryRepo categoryRepo,
			OrderRepo orderRepo,
			ProductRepo productRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.subscriptionRepo = subscriptionRepo;
		this.categoryRepo = categoryRepo; 
		this.orderRepo = orderRepo;
		this.productRepo = productRepo;
	}
}