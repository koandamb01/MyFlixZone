package com.David.javaProject.models.paypal;

import com.David.javaProject.models.general.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressRepo extends CrudRepository<ShippingAddress, Long>{
	List<ShippingAddress> findAll();

//	@Query("SELECT s FROM User u WHERE s.user= ?1")
//	List<Object[]> shipAddressesOfUser(User user);
}
