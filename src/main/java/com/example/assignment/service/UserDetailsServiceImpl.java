package com.example.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.assignment.entity.Authority;
import com.example.assignment.entity.User;
import com.example.assignment.repository.UserRepository;




/**
 * @author Suraj
 *
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			LOGGER.error("No user found with username", username);
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return create(user);
		}
	}
	
	
	private com.example.assignment.User create(User user) {
		return new com.example.assignment.User(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getPassword(), mapToGrantedAuthorities(user.getAuthorities()), user.getEnabled(),
				user.getLastPasswordResetDate(), user.getDeleted());
	}

	private  List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		for (Authority authority : authorities) {
			grantedAuthorityList.add(new SimpleGrantedAuthority(authority.getName().name()));
		}
		return grantedAuthorityList;
	}
}