package com.example.assignment.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.controller.URLShortener;
import com.example.assignment.entity.UrlMapping;
import com.example.assignment.repository.UrlRepository;
@Service
@Transactional
public class UrlMappingServiceImpl implements UrlMappingService {

	@Autowired
	private UrlRepository urlRepository;
	
	@Override
	public String getShortUrl(String url) {
		if (url != null && url != "") {
			UrlMapping  urlMapping = this.getUrlMappingDetailByLongUrl(url);
			if (urlMapping == null) {
				URLShortener u = new URLShortener(5, "www.sur.aj/");
				String tinyUrl = u.shortenURL(url);
				UrlMapping um = new UrlMapping();
				um.setLongUrl(url);
				um.setShortUrl(tinyUrl);
				save(um);
				return tinyUrl;
			} else {
				return urlMapping.getShortUrl();
			}
		} else {
			return "Please provide URl";
		}
	}
	
	
	@Override
	public UrlMapping getUrlMappingDetailByLongUrl(String longurl) {
		// TODO Auto-generated method stub
		UrlMapping user = urlRepository.findBylongUrl(longurl);
		return user;
	}

	@Override
	public void save(UrlMapping userurlmapping) {
		// TODO Auto-generated method stub
		urlRepository.save(userurlmapping);
	}

	@Override
	public UrlMapping getUrlMappingDetail(UrlMapping urlmapping) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UrlMapping getUrlMappingDetailByShortUrl(String shorturl) {
		// TODO Auto-generated method stub
		UrlMapping user = urlRepository.findBylongUrl(shorturl);
		return user;
	}
	
	@Override
	public String getLongUrl(String url) {
		if (url != null && url != "") {
			UrlMapping urlMapping = this.getUrlMappingDetailByShortUrl(url);
			System.out.println(urlMapping.toString());
			if (urlMapping != null) {
				return urlMapping.getLongUrl();
			}

		}
		return "Please provide URl";
	}

}
