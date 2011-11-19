package com.cit.eugene.service.business;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cit.eugene.model.Genre;
import com.cit.eugene.service.dao.GenreDAO;

public class GenreManagerImplTest {

	private GenreDAO genreRepository = null;
	private GenreManagerImpl genreManagerImpl = null;
	
	@Before
	public void setUp() throws Exception {
		genreRepository = createMock(GenreDAO.class);
		genreManagerImpl = new GenreManagerImpl();
		genreManagerImpl.init();
		genreManagerImpl.setGenreRepository(genreRepository);
	}
	
	@After
	public void tearDown() {
		genreManagerImpl.destroy();
	}

	@Test
	public void testSetGenreRepository() {
		assertNotNull(genreRepository);
		assertNotNull(genreManagerImpl);
	}

	@Test
	public void testGetGenreListing() {
		expect(genreRepository.getAllGenres()).andReturn(new ArrayList<Genre>());
		replay(genreRepository);
		assertNotNull(genreManagerImpl.getGenreListing());
		verify(genreRepository);
	}

}
