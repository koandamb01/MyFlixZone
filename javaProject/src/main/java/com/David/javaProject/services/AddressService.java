package com.David.javaProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.models.paypal.AddressRepo;

@Service
public class AddressService {
	@Autowired
	private AddressRepo addressRepo;
	
	
	// create a new address
	public Address createAddress(Address address) {
		return this.addressRepo.save(address);
	}
}
