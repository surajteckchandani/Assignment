package com.example.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.assignment.entity.User;

public interface EnableUserRepository extends CrudRepository<User, Long> {
	User findByusername(String username);
}