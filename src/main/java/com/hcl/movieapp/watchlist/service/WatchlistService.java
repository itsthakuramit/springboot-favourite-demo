package com.hcl.movieapp.watchlist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.hcl.movieapp.watchlist.model.MovieWatchList;
import com.hcl.movieapp.watchlist.model.Movies;
import com.hcl.movieapp.watchlist.repository.MovieWatchListRepo;

@Service
public class WatchlistService {

	@Autowired
	MovieWatchListRepo movieWatchListRepo;
	
	public boolean addToWatchList(Movies movie,String userid) {
		boolean status = false;
		
		if(!movieWatchListRepo.findById(userid).isEmpty()) {
			
			if(movieWatchListRepo.findById(userid).isPresent()){
			
				MovieWatchList moviewatchlist = movieWatchListRepo.findById(userid).get();
				List<Movies> movieslist = moviewatchlist.getMovielist();
				movieslist.add(movie);
				
				moviewatchlist.setMovielist(movieslist);
				movieWatchListRepo.save(moviewatchlist);
				status = true;
			}
		}else {
			List<Movies> movielist = new ArrayList<>();
			MovieWatchList moviewatchlist = new MovieWatchList();
			moviewatchlist.setUserid(userid);
			
			movielist.add(movie);
			
			moviewatchlist.setMovielist(movielist);
			movieWatchListRepo.save(moviewatchlist);
			status = true;
		}
		
		return status;
	}

	
	public List<Movies> getAllMovies(String userid){
		Optional<MovieWatchList> optList = movieWatchListRepo.findById(userid);
		if(optList.isPresent()) 	
			return movieWatchListRepo.findById(userid).get().getMovielist();
		else 
			return null;
	}
	
	public boolean removeMoviefromWatchList(Movies movie, String userid){
		boolean status = false;
		
		if(!movieWatchListRepo.findById(userid).isEmpty()) {
			List<Movies> movielist = movieWatchListRepo.findById(userid).get().getMovielist();
			
			ListIterator<Movies> listItr = movielist.listIterator();
			while (listItr.hasNext()) {
				Movies movies = (Movies) listItr.next();
				
				if(movies.getMovieid().equals(movie.getMovieid())) {
					listItr.remove();
				}
				
			}
			
			
			MovieWatchList moviewatchlist = new MovieWatchList();
			moviewatchlist.setUserid(userid);
			moviewatchlist.setMovielist(movielist);
			movieWatchListRepo.save(moviewatchlist);
			
			status = true;
		}
		
		return status;
	}
	
	
	
}
