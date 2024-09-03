package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	private final UserService userService;

	// Constructor initialization
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody UserEntity userEntity) {
		try {
			UserEntity user = userService.fetchUser(userEntity);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\":\"Invalid credentials\"}");
			}
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"An error occurred during login.\"}");
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> register(@RequestBody UserEntity userEntity) {
		try {
			userEntity.setUserType("student");
			userService.saveUser(userEntity);
			return ResponseEntity.ok("{\"message\":\"Registration Success!\"}");
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"An error occurred during registration: " + ex.getMessage() + "\"}");
		}
	}

	// Exception Handler to catch any unhandled exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\":\"" + ex.getMessage() + "\"}");
	}
}
