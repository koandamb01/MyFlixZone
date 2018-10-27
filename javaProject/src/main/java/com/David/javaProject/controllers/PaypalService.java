package com.David.javaProject.controllers;

import com.David.javaProject.models.general.RoleRepo;
import com.David.javaProject.models.general.SubscriptionRepo;
import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.paypal.*;
import org.springframework.stereotype.Service;



@Service
public class PaypalService {
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final SubscriptionRepo subscriptionRepo;
	private final CityRepo cityRepo;
	private final PaymentInfoRepo paymentInfoRepo;
	private final ShippingAddressRepo shippingAddressRepo;
	private final StateRepo stateRepo;
	private final ZipcodeRepo zipcodeRepo;
	
	public PaypalService(
			UserRepo userRepo, 
			RoleRepo roleRepo, 
			SubscriptionRepo subscriptionRepo, 
			CityRepo cityRepo, 
			PaymentInfoRepo paymentInfoRepo, 
			ShippingAddressRepo shippingAddressRepo,
			StateRepo stateRepo,
			ZipcodeRepo zipcodeRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.subscriptionRepo = subscriptionRepo;
		this.cityRepo = cityRepo;
		this.paymentInfoRepo = paymentInfoRepo;
		this.shippingAddressRepo = shippingAddressRepo;
		this.stateRepo = stateRepo;
		this.zipcodeRepo = zipcodeRepo;
	}

}
