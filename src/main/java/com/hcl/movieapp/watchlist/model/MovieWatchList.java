package com.hcl.movieapp.watchlist.model;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MovieWatchList {

	@Id
	private String userid;
	private List<Movies> movielist;
	
	public MovieWatchList() {
		
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Movies> getMovielist() {
		return movielist;
	}

	public void setMovielist(List<Movies> movielist) {
		this.movielist = movielist;
	}
	
	
	
	

}
