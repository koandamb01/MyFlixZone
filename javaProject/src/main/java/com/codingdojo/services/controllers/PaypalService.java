package com.codingdojo.services.controllers;

import org.springframework.stereotype.Service;

import com.codingdojo.models.general.RoleRepo;
import com.codingdojo.models.general.SubscriptionRepo;
import com.codingdojo.models.general.UserRepo;
import com.codingdojo.models.paypal.CityRepo;
import com.codingdojo.models.paypal.PaymentInfoRepo;
import com.codingdojo.models.paypal.ShippingAddressRepo;
import com.codingdojo.models.paypal.StateRepo;
import com.codingdojo.models.paypal.ZipcodeRepo;

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
