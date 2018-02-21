package com.example.assignment.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.assignment.entity.Authority;
import com.example.assignment.entity.AuthorityName;
import com.example.assignment.entity.UrlMapping;
import com.example.assignment.entity.User;
import com.example.assignment.service.UrlMappingService;
import com.example.assignment.service.Userservice;

@Controller
public class AssignmentController {

	@Autowired
	private Userservice userservice;

	@Autowired
	private UrlMappingService urlService;
	/**
	 * 
	 * @param userForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, Model model) {
		userservice.createUser(userForm);
		return "login";
	}
	
	/**
	 * 
	 * @param model
	 * @param principan
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model, Principal principan) {
		model.addAttribute("userForm", new User());
		return "registration";
	}
	/**
	 * 
	 * @param model
	 * @param error
	 * @param logout
	 * @return
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		return userservice.loginValidation(model, error, logout);
	}

	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/hello", "/login" })
	public String welcome(Model model) {
		return "hello";
	}
	/**
	 * 
	 * @param model
	 * @param pri
	 * @return
	 */
	@RequestMapping(value = { "/api" }, method = RequestMethod.GET)
	@ResponseBody
	public String api(Model model, Principal pri) {
		return userservice.generateApiKey(pri.getName());
	}

	
	/**
	 * This method is used to get short url
	 * @param url
	 * @param pri
	 * @return
	 */
	@RequestMapping(value = { "/shorternUrl" }, method = RequestMethod.POST)
	@ResponseBody
	public String shorternUrl(@RequestParam("url") String url, Principal pri) {
		return urlService.getShortUrl(url);
	}
	
	/**
	 * This method is used to get Long URL
	 * @param url
	 * @param pri
	 * @return
	 */
	@RequestMapping(value = { "/longUrl" }, method = RequestMethod.POST)
	@ResponseBody
	public String longUrl(@RequestParam("url") String url, Principal pri) {
		return urlService.getLongUrl(url);
	}

	

	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/403"})
	public String errorPage(Model model) {
		return "hello";
	}
}
