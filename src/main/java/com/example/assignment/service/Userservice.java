package com.example.assignment.service;

import org.springframework.ui.Model;

import com.example.assignment.entity.User;


public interface Userservice {
	
	
	User getUserDetail(User user);

	boolean updateUserDetail(User user);

	boolean addUserDetail(User user);

	void save(User userForm);
	
	User getUserByApiKey(String api_key);

	void createUser(User userForm);

	User getUserDetails(String username);

	String generateApiKey(String username);

	String loginValidation(Model model, String error, String logout);


}