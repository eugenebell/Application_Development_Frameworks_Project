package com.cit.eugene.service.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cit.eugene.model.Movie;

@Repository
public class JpaMovieDAO implements MovieDAO {

	private EntityManager entityManager;

	private static final String loadAllMovies = "from Movie";
	private static final String loadMoviebyID = "from Movie m where m.movieID = :movieID";
	private static final String loadMoviesByGenreID = "select m from Movie m join m.genres g where g.genreID = :genreID";

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Movie> getAllMovies() {
		// As this is only a demo and there won't be more than a 100 movie
		// objects lets cache them...
		return entityManager.createQuery(loadAllMovies).getResultList();
	}

	public List<Movie> getMovieListingByGenreID(Long genreID) {
		List<Movie> l = entityManager.createQuery(loadMoviesByGenreID).setParameter("genreID", genreID).getResultList();
		return l;
	}

	public Movie getMovieByID(Long movieID) {
		try {
			Movie m = (Movie) entityManager.createQuery(loadMoviebyID).setParameter("movieID", movieID).getSingleResult();
			return m;
		} catch (NoResultException e) {
			return null;
		}
	}
}
