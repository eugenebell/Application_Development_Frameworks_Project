package com.cit.eugene.service.dao;

import java.util.List;

import com.cit.eugene.model.Movie;

/**
 * Used to manage the Movie DAO layer.
 * 
 * @author Eugene
 */
public interface MovieDAO {

	/**
	 * Get all movies.
	 * 
	 * @return List<Movie>
	 */
	public List<Movie> getAllMovies();
	
	/**
	 * Get all movie by genre ID.
	 * 
	 * @param genreID
	 * @return List<Movie>
	 */
	public List<Movie> getMovieListingByGenreID(Long genreID);
	
	/**
	 * Get movie by id.
	 * 
	 * @param movieID
	 * @return Movie
	 */
	public Movie getMovieByID(Long movieID);
	
}
