package com.cit.eugene.service.business;

import java.util.List;

import com.cit.eugene.model.Movie;

/**
 * Class is for the Management of Movies.
 * 
 * @author Eugene
 */
public interface MovieManager {

	/**
	 * Returns the list of Movies.
	 * 
	 * @return List<Movie>
	 */
	public List<Movie> getMovieListing();
	
	/**
	 * Returns the list of Movies that match the selected genre.
	 * 
	 * @param genreID Long 
	 * @return List<Movie>
	 */
	public List<Movie> getMovieListingByGenreID(Long genreID);
	
	/**
	 * Returns the movie based on movieID and user name.
	 * 
	 * @param username String
	 * @param movieID Long
	 * @return Movie
	 */
	public Movie getMovieByID(String username, Long movieID);
	
}
