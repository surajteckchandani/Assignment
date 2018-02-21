package com.example.assignment.service;

import com.example.assignment.entity.UrlMapping;

public interface UrlMappingService {
	
	UrlMapping getUrlMappingDetail(UrlMapping urlmapping);
	UrlMapping getUrlMappingDetailByLongUrl(String  lngurl);
	UrlMapping getUrlMappingDetailByShortUrl(String  shorturl);
	void save(UrlMapping userurlmapping);
	String getShortUrl(String url);
	String getLongUrl(String url);

}
