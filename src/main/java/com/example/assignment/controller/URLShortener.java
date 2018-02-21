package com.example.assignment.controller;

import java.util.HashMap;
import java.util.Random;

public class URLShortener {
	private HashMap<String, String> keyMap;
	private HashMap<String, String> valueMap;
	private String domain;
	private char myChars[];
	private Random myRand;
	private int keyLength;

	/**
	 * Default Constructor
	 */
	URLShortener() {
		keyMap = new HashMap<String, String>();
		valueMap = new HashMap<String, String>();
		myRand = new Random();
		keyLength = 8;
		myChars = new char[62];
		for (int i = 0; i < 62; i++) {
			int j = 0;
			if (i < 10) {
				j = i + 48;
			} else if (i > 9 && i <= 35) {
				j = i + 55;
			} else {
				j = i + 61;
			}
			myChars[i] = (char) j;
		}
		domain = "http://fkt.in";
	}

	/**
	 * Constructor which enables you to define tiny URL key length and base URL
	 * 
	 * @param length
	 * @param newDomain
	 */
	public URLShortener(int length, String newDomain) {
		this();
		this.keyLength = length;
		if (!newDomain.isEmpty()) {
			newDomain = sanitizeURL(newDomain);
			domain = newDomain;
		}
	}

	/**
	 * the public method which can be called to shorten a given URL
	 * 
	 * @param longURL
	 * @return
	 */
	public String shortenURL(String longURL) {
		String shortURL = "";
		if (validateURL(longURL)) {
			longURL = sanitizeURL(longURL);
			if (valueMap.containsKey(longURL)) {
				shortURL = domain + "/" + valueMap.get(longURL);
			} else {
				shortURL = domain + "/" + getKey(longURL);
			}
		}
		return shortURL;
	}

	/**
	 * public method which returns back the original URL given the shortened url
	 * 
	 * @param shortURL
	 * @return
	 */
	public String expandURL(String shortURL) {
		String longURL = "";
		String key = shortURL.substring(domain.length() + 1);
		longURL = keyMap.get(key);
		return longURL;
	}

	/**
	 * To check is url is valid or not
	 * 
	 * @param url
	 * @return
	 */
	boolean validateURL(String url) {
		return true;
	}

	String sanitizeURL(String url) {
		if (url.substring(0, 7).equals("http://"))
			url = url.substring(7);

		if (url.substring(0, 8).equals("https://"))
			url = url.substring(8);

		if (url.charAt(url.length() - 1) == '/')
			url = url.substring(0, url.length() - 1);
		return url;
	}

	/**
	 * Get key
	 * 
	 * @param longURL
	 * @return
	 */
	private String getKey(String longURL) {
		String key;
		key = generateKey();
		keyMap.put(key, longURL);
		valueMap.put(longURL, key);
		return key;
	}

	/**
	 * Generate Key
	 * 
	 * @return
	 */
	private String generateKey() {
		String key = "";
		boolean flag = true;
		while (flag) {
			key = "";
			for (int i = 0; i <= keyLength; i++) {
				key += myChars[myRand.nextInt(62)];
			}
			if (!keyMap.containsKey(key)) {
				flag = false;
			}
		}
		return key;
	}

}
