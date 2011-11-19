package com.cit.eugene.web;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cit.eugene.model.Movie;
import com.cit.eugene.service.business.GenreManager;
import com.cit.eugene.service.business.MovieManager;

public class MovieControllerTest {

	private GenreManager genreManager = null;
	private MovieManager movieManager = null;
	private MovieController movieController = null;
	private Movie m = new Movie();
	
	@Before
	public void setUp() throws Exception {
		genreManager = createMock(GenreManager.class);
		movieManager = createMock(MovieManager.class);
		movieController = new MovieController(movieManager, genreManager);
		m.setMovieID(1l);
		m.setActorsDisplay("actorsDisplay");
		m.setDirectorsDisplay("directorsDisplay");
		m.setDisplayRunTime("160");
		m.setPrice(1.99);
		m.setRating(2);
		m.setTitle("title");
		m.setSummary("summary");
		m.setRented(false);
		m.setStudioDisplay("studioDisplay");
		m.setPosterFileName("posterFileName");
		m.setYear(1999);
	}

	@Test
	public void testGetMovieListByGenre() {
		expect(movieManager.getMovieListingByGenreID(1l)).andReturn(new ArrayList<Movie>());
		replay(movieManager);
		List<Movie> l = movieController.getMovieListByGenre(1l);
		assertNotNull(l);
		verify(movieManager);
	}
}
