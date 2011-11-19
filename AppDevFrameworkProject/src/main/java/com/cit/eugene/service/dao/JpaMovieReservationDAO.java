package com.cit.eugene.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cit.eugene.model.MovieReservation;

@Repository
@Transactional
public class JpaMovieReservationDAO implements MovieReservationDAO {

	private EntityManager entityManager;

	private static final String loadAllMovieReservationsByVideoStoreMember = "from MovieReservation mr where mr.memberID = :MemberID";

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public MovieReservation storeOrUpdateMovieReservation(MovieReservation movieReservation) {
		MovieReservation r = null;
		try {
			r = entityManager.merge(movieReservation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public void deleteMovieReservation(MovieReservation movieReservation) {
		movieReservation = entityManager.find(MovieReservation.class, movieReservation.getMovieReservationID());
		movieReservation.setMovie(null);
		movieReservation.setMemberID(null);
		if (movieReservation != null) {
			entityManager.remove(movieReservation);
		}
	}
}
