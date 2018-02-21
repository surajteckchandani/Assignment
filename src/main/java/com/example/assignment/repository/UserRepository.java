package com.example.assignment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.assignment.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("SELECT u FROM User u where u.username = :username and u.deleted = 0)")
	User findByUsername(@Param("username") String username);
	@Query("SELECT u FROM User u where u.api_key = :api_key and u.deleted = 0)")
	User findByapi_key(@Param("api_key") String api_key);
}
