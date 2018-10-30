package com.David.javaProject.models.music;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepo extends CrudRepository<Favorite, Long>{
	List<Favorite> findAll();
	
	@Query("SELECT fav FROM Favorite fav WHERE musicId = ?1")
	Favorite findByMusicId(Long id);
}