package com.example.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "url_mapping")
public class UrlMapping {

	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "url_seq")
    @SequenceGenerator(name = "url_seq", sequenceName = "url_seq", allocationSize = 1)
	@JsonIgnore
    private Long id;

    @Column(name = "longurl", length = 5000)
    @NotNull
    @Size(min = 4, max = 5000)
    private String longUrl;

    @Column(name = "shorturl", length = 1000)
    @NotNull
    @Size(min = 4, max = 1000)
    private String shortUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	@Override
	public String toString() {
		return "UrlMapping [id=" + id + ", longUrl=" + longUrl + ", shortUrl=" + shortUrl + "]";
	}
    
    
    
}
