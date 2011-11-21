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

import com.cit.eugene.model.Account;
import com.cit.eugene.model.Movie;
import com.cit.eugene.model.MovieReservation;
import com.cit.eugene.model.User;
import com.cit.eugene.model.VideoStoreMember;
import com.cit.eugene.service.dao.MovieDAO;
import com.cit.eugene.service.dao.MovieReservationDAO;
import com.cit.eugene.service.dao.VideoStoreMemberDAO;

public class VideoStoreMemberManagerImplTest {

	private VideoStoreMemberDAO videoStoreMemberRepository = null;
	private MovieDAO movieDAORepository = null;
	private MovieReservationDAO movieReservationRepository = null;
	private VideoStoreMemberManagerImpl movieManagerImpl = null;
	private Movie m = new Movie();
	private VideoStoreMember vsm = new VideoStoreMember();
	
	@Before
	public void setUp() throws Exception {
		videoStoreMemberRepository = createMock(VideoStoreMemberDAO.class);
		movieDAORepository = createMock(MovieDAO.class);
		movieReservationRepository = createMock(MovieReservationDAO.class);
		movieManagerImpl = new VideoStoreMemberManagerImpl();
		movieManagerImpl.init();
		movieManagerImpl.setMovieRepository(movieDAORepository);	
		movieManagerImpl.setVideoStoreMemberRepository(videoStoreMemberRepository);
		movieManagerImpl.setMovieReservationRepository(movieReservationRepository);
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
		Account a = new Account();
		a.setTotal(1.00);
		a.setAccountID(3l);
		vsm.setAccount(a);
		vsm.setMemebershipNumber("sdhu");
		vsm.setName("bob");
		vsm.setLocation("location");
		MovieReservation mr = new MovieReservation();
		mr.setMovie(m);
		mr.setMemberID(vsm);
		mr.setReservationDate(new Date());
		mr.setMovieReservationID(22l);
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
	public void testSetVideoStoreMemberRepository() {
		assertNotNull(videoStoreMemberRepository);
		assertNotNull(movieDAORepository);
	}

	@Test
	public void testStoreVideoStoreMember() {
		VideoStoreMember vsmNew = new VideoStoreMember();
		vsmNew.setLocation("location");
		vsmNew.setName("name");
		vsmNew.setVideoStoreMemberID(2l);
		vsmNew.setMemebershipNumber("22222");
		User u = new User();
		u.setPassword("password");
		u.setUsername("username");
		vsmNew.setUser(u);
		expect(videoStoreMemberRepository.storeVideoStoreMember(vsmNew)).andReturn(vsmNew);
		replay(videoStoreMemberRepository);
		VideoStoreMember expected = movieManagerImpl.storeVideoStoreMember(vsmNew);
		assertEquals(expected, vsmNew);
		assertEquals("location", expected.getLocation());
		assertEquals("name", expected.getName());
		assertEquals(Long.valueOf(2), expected.getVideoStoreMemberID());
		assertEquals("22222", expected.getMemebershipNumber());
		verify(videoStoreMemberRepository);
	}

//	@Test
//	public void testReserveMovie() {
//		MovieReservation mr = new MovieReservation();
//		mr.setMemberID(vsm);
//		Movie m = new Movie();
//		m.setMovieID(1l);
//		mr.setMovie(m);
//		mr.setRented(false);
//		mr.setReservationDate(new Date());
//		//expect(videoStoreMemberRepository.getVideoStoreMemberByName("bob")).andReturn(vsm);
//		expect(movieReservationRepository.storeOrUpdateMovieReservation(mr)).andReturn(mr);
//		//replay(videoStoreMemberRepository);
//		replay(movieReservationRepository);
//		movieManagerImpl.reserveMovie("bob", 1l, false);
//		//assertEquals(expected, true);
//		verify(movieReservationRepository);
//	}

	@Test
	public void testCancelReservedMovie() {
		expect(videoStoreMemberRepository.storeVideoStoreMember(vsm)).andReturn(vsm);
		expect(videoStoreMemberRepository.getVideoStoreMemberByName("bob")).andReturn(vsm);
		replay(videoStoreMemberRepository);
		boolean expected = movieManagerImpl.cancelReservedMovie("bob", 1l);
		assertEquals(expected, true);
		verify(videoStoreMemberRepository);
	}

	@Test
	public void testGetVideoStoreMember() {
		expect(videoStoreMemberRepository.getVideoStoreMemberByName("bob")).andReturn(vsm);
		replay(videoStoreMemberRepository);
		VideoStoreMember expected = movieManagerImpl.getVideoStoreMember("bob");
		assertEquals(expected, vsm);
		verify(videoStoreMemberRepository);
	}

	@Test
	public void testRentedMovie() {
		expect(videoStoreMemberRepository.storeVideoStoreMember(vsm)).andReturn(vsm);
		replay(videoStoreMemberRepository);
		boolean expected = movieManagerImpl.rentedMovie(vsm, 1l);
		assertEquals(expected, true);
		verify(videoStoreMemberRepository);
	}

	@Test
	public void testGetAllVideoStoreMember() {
		List<VideoStoreMember> l = new ArrayList<VideoStoreMember>();
		expect(videoStoreMemberRepository.getAllVideoStoreMembers()).andReturn(l);
		replay(videoStoreMemberRepository);
		List<VideoStoreMember> expected = movieManagerImpl.getAllVideoStoreMember();
		assertEquals(expected, l);
		verify(videoStoreMemberRepository);
	}

	@Test
	public void testGetVideoStoreMembersReservations() {
		expect(videoStoreMemberRepository.getVideoStoreMemberByID(2l)).andReturn(vsm);
		replay(videoStoreMemberRepository);
		List<MovieReservation> expected = movieManagerImpl.getVideoStoreMembersReservations(2l);
		assertNotNull(expected);
		verify(videoStoreMemberRepository);
	}

	@Test
	public void testGetVideoStoreMemberByID() {
		expect(videoStoreMemberRepository.getVideoStoreMemberByID(2l)).andReturn(vsm);
		replay(videoStoreMemberRepository);
		VideoStoreMember expected = movieManagerImpl.getVideoStoreMemberByID(2l);
		assertNotNull(expected);
		assertEquals("location", expected.getLocation());
		assertEquals("bob", expected.getName());
		assertEquals(Long.valueOf(2), expected.getVideoStoreMemberID());
		assertEquals("sdhu", expected.getMemebershipNumber());		
		assertNotNull(expected.getAccount());
		assertEquals(Double.valueOf(1.00), expected.getAccount().getTotal());
		assertEquals(Long.valueOf(3l), expected.getAccount().getAccountID());
		verify(videoStoreMemberRepository);
	}

}
