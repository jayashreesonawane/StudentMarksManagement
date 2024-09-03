package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	
	@Id
	private long id;
	
	@Column(name = "user_name", nullable = false, length = 20)
	private String userName;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	@Column(name = "user_type", nullable = false, length = 20) 
	private String userType;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	 public String getUserType() {
		 return userType; 
    }
	  
	 public void setUserType(String userType) {
		 this.userType = userType; 
	}
 
}
