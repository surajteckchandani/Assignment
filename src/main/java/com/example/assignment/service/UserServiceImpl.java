package com.example.assignment.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.assignment.entity.Authority;
import com.example.assignment.entity.AuthorityName;
import com.example.assignment.entity.UrlMapping;
import com.example.assignment.entity.User;
import com.example.assignment.repository.UserRepository;
@Service
@Transactional
public class UserServiceImpl implements Userservice {
	@Autowired
	private UserRepository userRepository;
	@Override
	public User getUserDetail(User user) {
		// TODO Auto-generated method stub
		return  userRepository.findByUsername(user.getUsername());
	}

	@Override
	public boolean updateUserDetail(User user) {
		// TODO Auto-generated method stub
		try {
			userRepository.save(user);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	
	@Override
	public void createUser(User userForm) {
		User user = new User();
		user.setUsername(userForm.getUsername());
		user.setFirstname(userForm.getFirstname());
		user.setLastname(userForm.getLastname());
		user.setEmail(userForm.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
		user.setLastPasswordResetDate(new Date());
		user.setEnabled(true);
		user.setDeleted(false);
		List<Authority> authorities = new ArrayList<>();
		Authority authority = new Authority();
		authority.setId((long) 1);
		authority.setName(AuthorityName.ROLE_USER);
		authorities.add(authority);
		user.setAuthorities(authorities);
		this.save(user);
	}
	
	
	@Override
	public boolean addUserDetail(User user) {
		// TODO Auto-generated method stub
		try {
			userRepository.save(user);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public void save(User userForm) {
		// TODO Auto-generated method stub
		userRepository.save(userForm);
	}

	@Override
	public User getUserByApiKey(String api_key) {
		// TODO Auto-generated method stub
		return  userRepository.findByapi_key(api_key);
		
	}
	
	
	/**
	 * This method is used to get current user details
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public User getUserDetails(String username) {
		User us = new User();
		us.setUsername(username);
		return this.getUserDetail(us);
	}
	
	@Override
	public String generateApiKey(final String username) {
		User user = this.getUserDetails(username);
		if (user.getApi_key() == null) {
			String str = user.getUsername() + ":" + user.getPassword();
			String encode = Base64.getEncoder().encodeToString(str.getBytes());
			user.setApi_key(encode.substring(0, 40));
			this.save(user);
		}

		return user.getApi_key();
	}
	
	
	
	@Override
	public String loginValidation(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");
		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		return "login";
	}

}
