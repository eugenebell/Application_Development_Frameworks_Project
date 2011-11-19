package com.cit.eugene.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MOVIE_RESERVATION")
public class MovieReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIE_RESERVATION_ID")
	private Long movieReservationID;

	@Column(name = "RENTED")
	private Boolean rented = false;

	@Column(name = "RESERVATION_DATE")
	@NotNull
	private Date reservationDate;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "VIDEO_STORE_MEMBER_ID")
	private VideoStoreMember memberID;

	@ManyToOne
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;

	public Long getMovieReservationID() {
		return movieReservationID;
	}

	public void setMovieReservationID(Long movieReservationID) {
		this.movieReservationID = movieReservationID;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public VideoStoreMember getMemberID() {
		return memberID;
	}

	public void setMemberID(VideoStoreMember memberID) {
		this.memberID = memberID;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Boolean getRented() {
		return rented;
	}

	public void setRented(Boolean rented) {
		this.rented = rented;
	}
}
