package com.David.javaProject.controllers;

import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.paypal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PaypalService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PaymentInfoRepo paymentInfoRepo;
	@Autowired
	private AddressRepo addressRepo;



}
