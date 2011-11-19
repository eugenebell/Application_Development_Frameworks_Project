package com.cit.eugene.service.dao;

import java.util.List;

import com.cit.eugene.model.VideoStoreMember;

/**
 * Used to manage the VideoStoreMember DAO layer.
 * 
 * @author Eugene
 */
public interface VideoStoreMemberDAO {

	/**
	 * Get All the Video Store members.
	 * 
	 * @param videoStore
	 * @return List<VideoStoreMember>
	 */
	public List<VideoStoreMember> getAllVideoStoreMembers();
	
	/**
	 * Delete VideoStoreMember.
	 * 
	 * @param videoStoreMemberID
	 */
	public void deleteVideoStoreMember(Long videoStoreMemberID);
	
	/**
	 * Get VideoStoreMember by ID.
	 * 
	 * @param videoStoreMemberID
	 * @return VideoStoreMember
	 */
	public VideoStoreMember getVideoStoreMemberByID(Long videoStoreMemberID);
	
	/**
	 * Get VideoStoreMember by name.
	 * 
	 * @param username
	 * @return VideoStoreMember
	 */
	public VideoStoreMember getVideoStoreMemberByName(String username);
	
	/**
	 * Store VideoStoreMember.
	 * 
	 * @param videoStoreMember
	 * @return stored VideoStoreMember
	 */
	public VideoStoreMember storeVideoStoreMember(VideoStoreMember videoStoreMember);
	
}
