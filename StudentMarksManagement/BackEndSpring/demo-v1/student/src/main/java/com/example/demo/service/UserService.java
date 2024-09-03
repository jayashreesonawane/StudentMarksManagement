package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	//login call
	public UserEntity fetchUser(UserEntity user) {
			
		UserEntity existingUser = userRepository.findByUserName(user.getUserName());
				
		if(existingUser!= null && existingUser.getPassword().equals(user.getPassword())) {
			return existingUser;
		}
		else {
			return null;
		}
			 
	}
	
	// register
	public void saveUser(UserEntity user) {
		System.out.println(userRepository.findById(user.getId()));
	    System.out.println(userRepository.findByUserName(user.getUserName()));
	    Optional<UserEntity> existingUserById = userRepository.findById(user.getId());

	    if(existingUserById.isEmpty() && userRepository.findByUserName(user.getUserName()) == null) {
			userRepository.save(user);
		}else {
			throw new UserFoundException("User already exists");
		}
	    

//	    UserEntity existingUser = userRepository.findByUserName(user.getUserName());
//	    if (existingUser == null) {
//	        // No user found with the given username, so it's safe to save the new user
//	        userRepository.save(user);
//	    } else {
//	        // A user with the given username already exists
//	        throw new UserFoundException("User already exists");
//	    }

	}


	
    //find by id
	public Optional<UserEntity> getUserById(Long id) {
	        return userRepository.findById(id);
	}
	
	//delete user
	public void deleteUser(Long id) {
	       userRepository.deleteById(id);
	}
	


}
