package com.David.javaProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.David.javaProject.models.paypal.AddressRepo;
import com.David.javaProject.models.paypal.PaymentInfoRepo;
import com.David.javaProject.repositories.UserRepository;



@Service
public class PaypalService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PaymentInfoRepo paymentInfoRepo;
	@Autowired
	private AddressRepo addressRepo;



}
