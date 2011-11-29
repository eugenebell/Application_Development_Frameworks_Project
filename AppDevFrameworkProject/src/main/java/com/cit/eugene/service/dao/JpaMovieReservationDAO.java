package com.cit.eugene.service.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cit.eugene.model.MovieReservation;

@Repository
@Transactional
public class JpaMovieReservationDAO implements MovieReservationDAO {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Secured("ROLE_USER")
	public MovieReservation storeOrUpdateMovieReservation(MovieReservation movieReservation) {
		return entityManager.merge(movieReservation);
	}

	@Secured("ROLE_USER")
	public void deleteMovieReservation(MovieReservation movieReservation) {
		MovieReservation movieRes = entityManager.find(MovieReservation.class, movieReservation.getMovieReservationID());
		if (movieRes != null) {
			movieRes.setMovie(null);
			movieRes.setMemberID(null);
			entityManager.remove(movieRes);
		}
	}
}
