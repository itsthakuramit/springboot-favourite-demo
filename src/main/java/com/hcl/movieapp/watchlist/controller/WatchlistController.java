package com.hcl.movieapp.watchlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.movieapp.watchlist.model.Movies;
import com.hcl.movieapp.watchlist.service.WatchlistService;

@RestController
@RequestMapping("/api/v1/movie")
public class WatchlistController {

	@Autowired
	WatchlistService watchService;
	
	@PostMapping("/{userid}/addToWatchList")
	public ResponseEntity<?> addToWatchList(@RequestBody Movies movie,@PathVariable String userid){
	
		if( watchService.addToWatchList(movie, userid))
			return new ResponseEntity<String>("Movie added to Watch list", HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Movie not added to Watchlist", HttpStatus.CONFLICT);
	
	}
	
	@GetMapping("/{userid}/getWatchList")
	public ResponseEntity<?> getWatchList(@PathVariable String userid){
		List<Movies> movielist = watchService.getAllMovies(userid);
		
		if(movielist != null)
			return new ResponseEntity<List<Movies>>(movielist, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Empty Watch list", HttpStatus.NOT_FOUND);
	
	}
	
	
	@DeleteMapping("/{userid}/deleteMoviefromWatchList")
	public ResponseEntity<?> dele(@RequestBody Movies movie,@PathVariable String userid){
		
		if( watchService.removeMoviefromWatchList(movie, userid))
			return new ResponseEntity<String>("Movie delete from Watch list", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Movie not deleted from Watchlist", HttpStatus.CONFLICT);
	
	}
	
	
}
