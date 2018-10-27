package com.David.javaProject.models.music;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepo extends CrudRepository<Favorite, Long>{
	List<Favorite> findAll();
}