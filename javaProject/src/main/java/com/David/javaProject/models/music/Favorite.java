package com.David.javaProject.models.music;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.David.javaProject.models.general.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="music_favorites")
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long musicId;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}
    

	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    	name="users_favorites",
    	joinColumns = @JoinColumn(name="favorite_id"),
    	inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> users = new ArrayList<>();
	
	
	
	public Favorite() {}
	
	public Favorite(Long musicId) {
		this.musicId = musicId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public Long getMusic_id() {
		return musicId;
	}
	public void setMusicId(Long musicId) {
		this.musicId = musicId;
	}
	
	
	
}
