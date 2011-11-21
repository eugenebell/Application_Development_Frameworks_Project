package com.cit.eugene.service.business;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cit.eugene.model.Movie;
import com.cit.eugene.model.MovieReservation;
import com.cit.eugene.model.VideoStoreMember;
import com.cit.eugene.service.dao.MovieDAO;
import com.cit.eugene.service.dao.VideoStoreMemberDAO;

public class MovieManagerImplTest {

	private MovieDAO movieDAORepository = null;
	private MovieManagerImpl movieManagerImpl = null;
	private VideoStoreMemberDAO videoStoreMemberRepository = null;
	private Movie m = new Movie();
	private VideoStoreMember vsm = new VideoStoreMember();
	
	@Before
	public void setUp() throws Exception {
		movieDAORepository = createMock(MovieDAO.class);
		movieManagerImpl = new MovieManagerImpl();
		videoStoreMemberRepository = createMock(VideoStoreMemberDAO.class);
		movieManagerImpl.init();
		movieManagerImpl.setMovieRepository(movieDAORepository);	
		movieManagerImpl.setVideoStoreMemberDAO(videoStoreMemberRepository);
		m.setMovieID(1l);
		m.setActorsDisplay("actorsDisplay");
		m.setDirectorsDisplay("directorsDisplay");
		m.setProducersDisplay("Bob Nobody");
		m.setDisplayRunTime("160");
		m.setPrice(1.99);
		m.setRating(2);
		m.setTitle("title");
		m.setSummary("summary");
		m.setRented(false);
		m.setStudioDisplay("studioDisplay");
		m.setPosterFileName("posterFileName");
		m.setYear(1999);
		vsm.setMemebershipNumber("sdhu");
		vsm.setName("bob");
		MovieReservation mr = new MovieReservation();
		mr.setMovie(m);
		mr.setMemberID(vsm);
		mr.setReservationDate(new Date());
		List<MovieReservation> l = new ArrayList<MovieReservation>();
		l.add(mr);
		vsm.setMovieReservations(l);
		vsm.setVideoStoreMemberID(2l);
	}
	
	@After
	public void tearDown() {
		movieManagerImpl.destroy();
	}

	@Test
	public void testSetMovieRepository() {
		assertNotNull(movieDAORepository);
		assertNotNull(movieManagerImpl);
	}

	@Test
	public void testGetMovieListing() {
		expect(movieDAORepository.getAllMovies()).andReturn(new ArrayList<Movie>());
		replay(movieDAORepository);
		List<Movie> l2 = movieManagerImpl.getMovieListing();
		assertNotNull(l2);
		verify(movieDAORepository);
	}

	@Test
	public void testGetMovieListingByGenreID() {
		List<Movie> l = new ArrayList<Movie>();
		l.add(m);
		expect(movieDAORepository.getMovieListingByGenreID(1l)).andReturn(l);
		replay(movieDAORepository);
		List<Movie> l2 = movieManagerImpl.getMovieListingByGenreID(1l);
		assertNotNull(l2);
		verify(movieDAORepository);
	}

	@Test
	public void testGetMovieByID() {
		expect(videoStoreMemberRepository.getVideoStoreMemberByName("bob")).andReturn(vsm);
		expect(movieDAORepository.getMovieByID(1l)).andReturn(m);
		replay(videoStoreMemberRepository);
		replay(movieDAORepository);
		assertEquals(m, movieManagerImpl.getMovieByID("bob", 1l));
		verify(movieDAORepository);
	}

}
