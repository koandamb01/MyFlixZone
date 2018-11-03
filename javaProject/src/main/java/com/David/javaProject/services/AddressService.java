package com.David.javaProject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.models.paypal.AddressRepo;

@Service
public class AddressService {
	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private ShoppingService shoppingService;
	
	// create a new address
	public Address createAddress(User user, Address address) {
		Address newAddress = this.addressRepo.save(address);
		
		if(newAddress.isDefaultShippingAddress()) {
			
			Address defaultAddress = this.shoppingService.findDefaultAddress(user);
			
			if(defaultAddress != null) {
				this.shoppingService.findDefaultAddress(user).setDefaultShippingAddress(false);
				this.userService.updatedPersonal(user);
			}
		}
		
//		user.getAddresses().add(newAddress);
		newAddress.setUser(user);
		this.addressRepo.save(newAddress);
		this.userService.updatedPersonal(user);
		return newAddress;
	}
	
	
	
	// Set default address
	public Address setDefaultAddress(User user, Address address) {
		//find the address by id from parameter
		Optional<Address> optional = this.addressRepo.findById(address.getId());
		
		if(optional.isPresent()){
			Address oldAddress = optional.get();
			
			//find default address and set it to false
			this.shoppingService.findDefaultAddress(user).setDefaultShippingAddress(false);
			this.userService.updatedPassword(user);
			
			//set the new address to true
			oldAddress.setDefaultShippingAddress(true);
			this.addressRepo.save(address);
			
			return oldAddress;
		}
		else {
			return null;
		}
	}
	
	
	// find address by id
	public Address findAddressById(Long id) {
		
		//find the address by id from parameter
		Optional<Address> optional = this.addressRepo.findById(id);
		
		if(optional.isPresent()){
			return optional.get();
		}
		else {
			return null;
		}
	}
}
