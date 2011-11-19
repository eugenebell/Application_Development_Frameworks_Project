package com.cit.eugene.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "VIDEO_STORE_MEMBER")
public class VideoStoreMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VIDEO_STORE_MEMBER_ID")
	private Long videoStoreMemberID;

	@NotNull
	@Column(name = "MEMBER_NAME")
	private String name;

	@NotNull
	@Column(name = "LOCATION")
	private String location;

	@NotNull
	@Column(name = "MEMBERSHIP_NUMBER")
	private String memebershipNumber;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USERNAME")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "memberID", orphanRemoval = true)
	private List<MovieReservation> movieReservations = new ArrayList<MovieReservation>();

	public Long getVideoStoreMemberID() {
		return videoStoreMemberID;
	}

	public void setVideoStoreMemberID(Long videoStoreMemberID) {
		this.videoStoreMemberID = videoStoreMemberID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMemebershipNumber() {
		return memebershipNumber;
	}

	public void setMemebershipNumber(String memebershipNumber) {
		this.memebershipNumber = memebershipNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	// To stop JSON going nuts on recursion
	@JsonIgnore
	public List<MovieReservation> getMovieReservations() {
		return movieReservations;
	}

	public void setMovieReservations(List<MovieReservation> movieReservations) {
		this.movieReservations = movieReservations;
	}
}
