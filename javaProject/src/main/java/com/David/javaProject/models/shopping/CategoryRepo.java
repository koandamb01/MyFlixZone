package com.David.javaProject.models.shopping;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Long>{
	List<Category> findAll();
}
