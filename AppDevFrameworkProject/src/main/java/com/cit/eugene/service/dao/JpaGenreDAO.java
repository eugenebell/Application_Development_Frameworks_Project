package com.cit.eugene.service.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cit.eugene.model.Genre;

@Repository
public class JpaGenreDAO implements GenreDAO {

	private EntityManager entityManager;
	
    private static final String loadAllGenres = "from Genre";

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Genre> getAllGenres() {
		return entityManager.createQuery(loadAllGenres).getResultList();
	}

}
