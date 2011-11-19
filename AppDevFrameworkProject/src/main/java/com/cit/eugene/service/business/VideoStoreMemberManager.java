package com.cit.eugene.service.business;

import java.util.List;

import com.cit.eugene.model.MovieReservation;
import com.cit.eugene.model.VideoStoreMember;

/**
 * Class is for the Management of VidepStoreMember.
 * 
 * @author Eugene
 */
public interface VideoStoreMemberManager {

	/**
	 * Returns all the VideoStoreMembers.
	 * 
	 * @return List<VideoStoreMember>
	 */
	public List<VideoStoreMember> getAllVideoStoreMember();
	
	/**
	 * Returns VideoStoreMember based on name.
	 * 
	 * @param videoStoreMemberName
	 * @return VideoStoreMember
	 */
	public VideoStoreMember getVideoStoreMember(String videoStoreMemberName);
	
	/**
	 * Returns VideoStoreMember based on ID.
	 * 
	 * @param videoStoreMemberID
	 * @return VideoStoreMember
	 */
	public VideoStoreMember getVideoStoreMemberByID(Long videoStoreMemberID);
	
	/**
	 * Delete VideoStoreMember by ID.
	 * 
	 * @param videoStoreMemberID
	 */
	public void deleteVideoStoreMember(Long videoStoreMemberID);
	
	/**
	 * Returns List of MovieReservation based on VideoStoreMember ID.
	 * 
	 * @param videoStoreMemberID
	 * @return List<MovieReservation>
	 */
	public List<MovieReservation> getVideoStoreMembersReservations(Long videoStoreMemberID);
	
	/**
	 * Store MovieReservation
	 * 
	 * @param videoStoreMember
	 * @return VideoStoreMember the store version.
	 */
	public VideoStoreMember storeVideoStoreMember(VideoStoreMember videoStoreMember);
	
	/**
	 * Reserve the movie based on user, movie id.
	 * 
	 * @param username
	 * @param movieID
	 * @param rented
	 * @return boolean if it has succeeded
	 */
	public boolean reserveMovie(String username, long movieID, boolean rented);
	
	/**
	 * Cancel the reserved Movie.
	 *  
	 * @param username
	 * @param reservationID
	 * @return boolean if it has succeeded
	 */
	public boolean cancelReservedMovie(String username, long reservationID);
	
	/**
	 * Movie has been rented.
	 * 
	 * @param vsm
	 * @param reservationID
	 * @return boolean if it has succeeded
	 */
	public boolean rentedMovie(VideoStoreMember vsm, long reservationID);
}
