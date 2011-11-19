package com.cit.eugene.service.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cit.eugene.model.Movie;
import com.cit.eugene.model.MovieReservation;
import com.cit.eugene.model.VideoStoreMember;
import com.cit.eugene.service.dao.MovieDAO;
import com.cit.eugene.service.dao.VideoStoreMemberDAO;

@Service
public class MovieManagerImpl implements MovieManager {

	private static final Logger LOG = Logger.getLogger(MovieManagerImpl.class);
	
	@Autowired
	private MovieDAO movieRepository;
	
	@Autowired
	private VideoStoreMemberDAO videoStoreMemberRepository;
	
	@PostConstruct
	void init() {
		LOG.info("MovieManagerImpl Has been Created");
	} 
	
	@PreDestroy
	void destroy() {
		LOG.info("MovieManagerImpl Has been Destroyed");
	}
	
	public void setMovieRepository(MovieDAO movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	public void setVideoStoreMemberDAO(VideoStoreMemberDAO videoStoreMemberRepository) {
		this.videoStoreMemberRepository = videoStoreMemberRepository;
	}

	@Transactional(readOnly=true)
	public List<Movie> getMovieListing() {
		return movieRepository.getAllMovies();
	}
	
	@Transactional(readOnly=true)
	public List<Movie> getMovieListingByGenreID(Long genreID) {
		return movieRepository.getMovieListingByGenreID(genreID);
	}

	@Transactional(readOnly=true)
	public Movie getMovieByID(String username, Long movieID) {
		VideoStoreMember vsm = videoStoreMemberRepository.getVideoStoreMemberByName(username);
		Movie movie = movieRepository.getMovieByID(movieID);
		List<MovieReservation> l = vsm.getMovieReservations();
		for (MovieReservation movieReservation : l) {
				if (movieReservation.getMovie().getMovieID().equals(movie.getMovieID())) {
					movie.setRented(true);
				}
		}
		return movie;
	}
}
