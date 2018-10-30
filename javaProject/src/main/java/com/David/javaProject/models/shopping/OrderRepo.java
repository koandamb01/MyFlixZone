package com.David.javaProject.models.shopping;

import com.David.javaProject.models.general.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long>{
	List<Order> findAll();
	Order findByStatusAndUser(String status, User user);
}
