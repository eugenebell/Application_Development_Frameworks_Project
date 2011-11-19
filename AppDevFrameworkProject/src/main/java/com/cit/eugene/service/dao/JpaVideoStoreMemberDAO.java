package com.cit.eugene.service.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cit.eugene.model.VideoStoreMember;

@Repository
public class JpaVideoStoreMemberDAO implements VideoStoreMemberDAO {

	private EntityManager entityManager;

	private static final String loadAllVideoStoreMembers = "from VideoStoreMember";
	private static final String loadVideoStoreMembersByID = "from VideoStoreMember vsm where vsm.videoStoreMemberID = :videoStoreMemberID";
	private static final String loadVideoStoreMembersByName = "from VideoStoreMember vsm where vsm.name = :username";

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<VideoStoreMember> getAllVideoStoreMembers() {
		return entityManager.createQuery(loadAllVideoStoreMembers).getResultList();
	}

	public VideoStoreMember getVideoStoreMemberByID(Long videoStoreMemberID) {
		return (VideoStoreMember) entityManager.createQuery(loadVideoStoreMembersByID).setParameter("videoStoreMemberID", videoStoreMemberID).getSingleResult();
	}

	public VideoStoreMember storeVideoStoreMember(VideoStoreMember videoStoreMember) {
		return entityManager.merge(videoStoreMember);
	}

	public VideoStoreMember getVideoStoreMemberByName(String username) {
		try {
			VideoStoreMember v = (VideoStoreMember) entityManager.createQuery(loadVideoStoreMembersByName).setParameter("username", username).getSingleResult();
			return v;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteVideoStoreMember(Long videoStoreMemberID) {
		VideoStoreMember vsm = entityManager.find(VideoStoreMember.class, videoStoreMemberID);
		if (vsm != null) {
			entityManager.remove(vsm);
		}
	}
}
