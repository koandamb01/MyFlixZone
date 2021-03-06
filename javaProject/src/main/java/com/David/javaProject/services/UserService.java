package com.David.javaProject.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.David.javaProject.models.general.User;
import com.David.javaProject.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	// register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    
    // get all the users
    public List<User> findAllUsers(){
    	return this.userRepo.findAll();
    }
    
    
    // updated a user
    public User updatedPersonal(User user) {
    	return this.userRepo.save(user);
    }
    
    // updated a user password
    public User updatedPassword(User user) {
    	// get the user object
        User u = this.findUserById(user.getId());
    
        // if the passwords match
        if(BCrypt.checkpw(user.getOldPassword(), u.getPassword())) {
            String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            u.setPassword(hashed);
        	return this.userRepo.save(u);
        } else {
            return null;
        }
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
