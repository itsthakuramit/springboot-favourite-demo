package com.hcl.movieapp.watchlist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcl.movieapp.watchlist.model.MovieWatchList;

@Repository
public interface MovieWatchListRepo extends MongoRepository<MovieWatchList, String> {

}
