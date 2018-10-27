package com.codingdojo.models.paypal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepo extends CrudRepository<ShippingAddress, Long>{
	List<ShippingAddress> findAll();
}
