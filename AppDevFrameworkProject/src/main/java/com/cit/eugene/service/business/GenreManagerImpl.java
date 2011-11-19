package com.cit.eugene.service.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cit.eugene.model.Genre;
import com.cit.eugene.service.dao.GenreDAO;

@Service
public class GenreManagerImpl implements GenreManager {

	private static final Logger LOG = Logger.getLogger(GenreManagerImpl.class);
	
	@Autowired
	private GenreDAO genreRepository;
	
	@PostConstruct
	void init() {
		LOG.info("GenreManagerImpl Has been Created");
	}
	
	@PreDestroy
	void destroy() {
		LOG.info("GenreManagerImpl Has been Destroyed");
	}

	public void setGenreRepository(GenreDAO genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Transactional(readOnly=true)
	public List<Genre> getGenreListing() {
		return genreRepository.getAllGenres();
	}
	
}
