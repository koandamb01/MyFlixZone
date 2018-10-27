package com.David.javaProject.models.general;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepo extends CrudRepository<Subscription, Long>{
	List<Subscription> findAll();
}