package com.codingdojo.models.paypal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepo extends CrudRepository<State, Long>{
	List<State> findAll();
}
