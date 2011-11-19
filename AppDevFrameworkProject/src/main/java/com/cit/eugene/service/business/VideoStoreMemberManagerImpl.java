package com.cit.eugene.service.business;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cit.eugene.model.Account;
import com.cit.eugene.model.Authorities;
import com.cit.eugene.model.MovieReservation;
import com.cit.eugene.model.VideoStoreMember;
import com.cit.eugene.service.dao.MovieDAO;
import com.cit.eugene.service.dao.MovieReservationDAO;
import com.cit.eugene.service.dao.VideoStoreMemberDAO;

@Service
public class VideoStoreMemberManagerImpl implements VideoStoreMemberManager {

	private static final Logger LOG = Logger.getLogger(VideoStoreMemberManagerImpl.class);

	// @Autowired
	// private SaltSource mySalt;

	@Autowired
	private MovieDAO movieRepository;
	
	@Autowired
	private MovieReservationDAO movieReservationRepository;

	@Autowired
	private VideoStoreMemberDAO videoStoreMemberRepository;

	@PostConstruct
	void init() {
		LOG.info("VideoStoreMemberManagerImpl Has been Created");
	}

	@PreDestroy
	void destroy() {
		LOG.info("VideoStoreMemberManagerImpl Has been Destroyed");
	}

	public void setVideoStoreMemberRepository(VideoStoreMemberDAO videoStoreMemberRepository) {
		this.videoStoreMemberRepository = videoStoreMemberRepository;
	}	

	public void setMovieReservationRepository(MovieReservationDAO movieReservationRepository) {
		this.movieReservationRepository = movieReservationRepository;
	}

	public void setMovieRepository(MovieDAO movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public VideoStoreMember storeVideoStoreMember(VideoStoreMember videoStoreMember) {
		// PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		// String pwd =
		// passwordEncoder.encodePassword(videoStoreMember.getUser().getPassword(),
		// "MySalt");
		Authorities a = new Authorities();
		a.setAuthority("ROLE_USER");
		a.setUsers(videoStoreMember.getUser());
		Set<Authorities> s = new HashSet<Authorities>();
		s.add(a);
		Account account = new Account(0.0);
		videoStoreMember.setAccount(account);
		videoStoreMember.getUser().setAuthoritieses(s);
		videoStoreMember.getUser().setEnabled(true);
		return videoStoreMemberRepository.storeVideoStoreMember(videoStoreMember);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean reserveMovie(String username, long movieID, boolean rented) {
		VideoStoreMember vsm = getVideoStoreMember(username);
		MovieReservation mr = new MovieReservation();
		mr.setMemberID(vsm);
		mr.setRented(rented);
		mr.setMovie(movieRepository.getMovieByID(movieID));
		mr.setReservationDate(Calendar.getInstance().getTime());
		movieReservationRepository.storeOrUpdateMovieReservation(mr);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean cancelReservedMovie(String username, long reservationID) {
		VideoStoreMember vsm = getVideoStoreMember(username);
		List<MovieReservation> l = vsm.getMovieReservations();
		MovieReservation toBeRemoved = null;
		for (MovieReservation mr : l) {
			if (!mr.getRented()) {
				if (mr.getMovie().getMovieID().longValue() == reservationID) {
					toBeRemoved = mr;
					break;
				}
			}
		}
		if (toBeRemoved != null) {
			vsm.getMovieReservations().remove(toBeRemoved);
		}
		videoStoreMemberRepository.storeVideoStoreMember(vsm);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public VideoStoreMember getVideoStoreMember(String videoStoreMemberName) {
		return videoStoreMemberRepository.getVideoStoreMemberByName(videoStoreMemberName);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean rentedMovie(VideoStoreMember vsm, long reservationID) {
		List<MovieReservation> l = vsm.getMovieReservations();
		for (MovieReservation mr : l) {
			if (mr.getMovieReservationID() == reservationID) {
				//Add the cost of the movie to the users account total
				Account acc = vsm.getAccount();
				double newTotal = acc.getTotal() + mr.getMovie().getPrice();
				acc.setTotal(newTotal);
				vsm.setAccount(acc);
				mr.setRented(true);
			}
		}
		videoStoreMemberRepository.storeVideoStoreMember(vsm);
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<VideoStoreMember> getAllVideoStoreMember() {
		return videoStoreMemberRepository.getAllVideoStoreMembers();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<MovieReservation> getVideoStoreMembersReservations(Long videoStoreMemberID) {
		return videoStoreMemberRepository.getVideoStoreMemberByID(videoStoreMemberID).getMovieReservations();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteVideoStoreMember(Long videoStoreMemberID) {
		videoStoreMemberRepository.deleteVideoStoreMember(videoStoreMemberID);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public VideoStoreMember getVideoStoreMemberByID(Long videoStoreMemberID) {
		return videoStoreMemberRepository.getVideoStoreMemberByID(videoStoreMemberID);
	}
}
