package com.cit.eugene.service.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import com.cit.eugene.model.Genre;
import com.cit.eugene.model.Movie;

@Repository
public class JpaMovieDAO implements MovieDAO {

	private EntityManager entityManager;

	private static final String loadAllMovies = "from Movie";
	private static final String loadMoviebyID = "from Movie m where m.movieID = :movieID";
	private static final String loadMoviesByGenreID = "from Genre g where g.genreID = :genreID";

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"}) 
	@SuppressWarnings("unchecked")
	public List<Movie> getAllMovies() {
		// As this is only a demo and there won't be more than a 100 movie
		// objects lets cache them...
		return entityManager.createQuery(loadAllMovies).getResultList();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"}) 
	public List<Movie> getMovieListingByGenreID(Long genreID) {
		Genre g = (Genre) entityManager.createQuery(loadMoviesByGenreID).setParameter("genreID", genreID).getSingleResult();
		return new ArrayList<Movie>(g.getMovies());
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"}) 
	public Movie getMovieByID(Long movieID) {
		try {
			Movie m = (Movie) entityManager.createQuery(loadMoviebyID).setParameter("movieID", movieID).getSingleResult();
			return m;
		} catch (NoResultException e) {
			return null;
		}
	}
}
