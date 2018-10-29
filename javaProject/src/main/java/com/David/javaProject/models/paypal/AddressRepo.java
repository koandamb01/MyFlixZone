package com.David.javaProject.models.paypal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends CrudRepository<Address, Long>{
	List<Address> findAll();

//	@Query("SELECT s FROM User u WHERE s.user= ?1")
//	List<Object[]> shipAddressesOfUser(User user);
}
