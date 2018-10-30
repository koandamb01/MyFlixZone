package com.David.javaProject.models.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.David.javaProject.models.general.User;
import com.David.javaProject.repositories.UserRepository;

@Service
public class FavoriteService {
	@Autowired
	private FavoriteRepo favRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	// create a new favorite
	public void favoriteAmusic(User user, Long musicId) {
		if(this.isMusicBeenFavorited(musicId)) {
			Favorite fav = this.findFavoriteByMusicId(musicId);
			user.getFavorites().add(fav);
			this.userRepo.save(user);
			
		}else {
			Favorite newFav = new Favorite(musicId);
			newFav = this.favRepo.save(newFav);
			user.getFavorites().add(newFav);
			this.userRepo.save(user);
		}
		
	}
	
	
	// check this music all been favorited
	public boolean isMusicBeenFavorited(Long musicId) {
		if(this.favRepo.findByMusicId(musicId) == null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	// get a favorite music by external music id
	public Favorite findFavoriteByMusicId(Long musicId) {
		return this.favRepo.findByMusicId(musicId);
	}
	
}
