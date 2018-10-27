package com.David.javaProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.David.javaProject.models.User;

public interface UserRepository extends JpaRepository <User, Long>{

}
