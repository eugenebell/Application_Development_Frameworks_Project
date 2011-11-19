package com.cit.eugene.service.dao;

import java.util.List;

import com.cit.eugene.model.Genre;

/**
 * Used to manage the Genre DAO layer.
 * 
 * @author Eugene
 */
public interface GenreDAO {

	/**
	 * Get all genres.
	 * 
	 * @return List<Genre>
	 */
	public List<Genre> getAllGenres();
	
}
