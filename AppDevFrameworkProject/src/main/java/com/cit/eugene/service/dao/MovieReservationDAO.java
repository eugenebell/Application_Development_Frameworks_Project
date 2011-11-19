package com.cit.eugene.service.dao;

import com.cit.eugene.model.MovieReservation;

/**
 * Used to manage the MovieReservation DAO layer.
 * 
 * @author Eugene
 */
public interface MovieReservationDAO {
	
	/**
	 * Store or Update Movie reservation.
	 * 
	 * @param movieReservation
	 * @return MovieReservation
	 */
	public MovieReservation storeOrUpdateMovieReservation(MovieReservation movieReservation);
	
	/**
	 * Delete Movie Reservation.
	 * 
	 * @param movieReservation
	 */
	public void deleteMovieReservation(MovieReservation movieReservation);
}
