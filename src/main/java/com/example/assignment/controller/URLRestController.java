package com.example.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.entity.Status;
import com.example.assignment.entity.UrlMapping;
import com.example.assignment.entity.User;
import com.example.assignment.service.UrlMappingService;
import com.example.assignment.service.Userservice;

@RestController
@RequestMapping("/rest")
public class URLRestController {

	@Autowired
	private UrlMappingService urlService;
	@Autowired
	private Userservice userservice;
	/**
	 * 
	 * @param url
	 * @param api_key
	 * @return
	 */
	@RequestMapping(value = "/getShortUrl", method = RequestMethod.POST)
	public ResponseEntity<?> getShortUrl(@RequestBody UrlMapping url, @RequestHeader("api_key") String api_key) {
		boolean auth = checkAuth(api_key);
		if (auth) {
			if (url != null && auth) {
				UrlMapping um = urlService.getUrlMappingDetailByShortUrl(url.getLongUrl());
				return new ResponseEntity<UrlMapping>(um, HttpStatus.OK);
			}
		} else if (!auth) {
			Status st = new Status();
			st.setStatuscode(401);
			st.setMessage("API Key provided is invalid");
			return new ResponseEntity<Status>(st, HttpStatus.UNAUTHORIZED);
		}
		return null;
	}
	/**
	 * 
	 * @param url
	 * @param api_key
	 * @return
	 */
	@RequestMapping(value = "/getLongUrl", method = RequestMethod.POST)
	public ResponseEntity<?> getLongUrl(@RequestBody UrlMapping url, @RequestHeader("api_key") String api_key) {
		System.out.println("getLongUrl");
		boolean auth = checkAuth(api_key);
		System.out.println(auth);
		if (auth) {
			if (url != null) {
				UrlMapping um = urlService.getUrlMappingDetailByShortUrl(url.getShortUrl());
				return new ResponseEntity<UrlMapping>(um, HttpStatus.OK);
			}
		} else if (!auth) {
			Status st = new Status();
			st.setStatuscode(401);
			st.setMessage("API Key provided is invalid");
			return new ResponseEntity<Status>(st, HttpStatus.UNAUTHORIZED);
		}
		return null;
	}
	/**
	 * 
	 * @param api_key
	 * @return
	 */
	public boolean checkAuth(String api_key) {
		User us = userservice.getUserByApiKey(api_key);
		if (us != null) {
			return true;
		} else {
			return false;
		}
	}
}
