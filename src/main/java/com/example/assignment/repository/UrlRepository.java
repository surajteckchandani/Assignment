package com.example.assignment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.assignment.entity.UrlMapping;

public interface UrlRepository  extends CrudRepository<UrlMapping, Long> {
	
	@Query("SELECT u FROM UrlMapping u where u.longUrl = :longUrl)")
	UrlMapping findBylongUrl(@Param("longUrl") String longUrl);
	
	@Query("SELECT u FROM UrlMapping u where u.shortUrl = :shortUrl)")
	UrlMapping findByshortUrl(@Param("shortUrl") String shortUrl);
}
