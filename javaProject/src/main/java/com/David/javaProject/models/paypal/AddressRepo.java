package com.David.javaProject.models.paypal;

import com.David.javaProject.models.general.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends CrudRepository<Address, Long>{
	List<Address> findAll();
	Address findByStreetAndCityAndStateAndZipcodeAndUser(String street, String city, String state, String zipcode, User user);

}
