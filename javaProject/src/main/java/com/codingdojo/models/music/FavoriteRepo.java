package com.codingdojo.models.music;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepo extends CrudRepository<Favorite, Long>{
	List<Favorite> findAll();
}