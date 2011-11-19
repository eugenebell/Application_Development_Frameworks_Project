package com.cit.eugene.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MOVIE")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIE_ID")
	private Long movieID;

	@Column(name = "RATING")
	private Integer rating;

	@Column(name = "PRICE")
	@NotNull
	private Double price;

	@Column(name = "DISPLAY_RUNTIME")
	private String displayRunTime;

	@Column(name = "YEAR")
	private Integer year;

	@Column(name = "TITLE")
	@NotNull
	private String title;

	@Column(name = "SUMMARY")
	@NotNull
	private String summary;

	@Column(name = "POSTER_FILENAME")
	@NotNull
	private String posterFileName;

	@Column(name = "ACTORS_DISPLAY")
	private String actorsDisplay;

	@Column(name = "DIRECTORS_DISPLAY")
	private String directorsDisplay;

	@Column(name = "PRODUCERS_DISPLAY")
	private String producersDisplay;

	@Column(name = "STUDIO_DISPLAY")
	private String studioDisplay;

	private transient boolean rented = false;

	@ManyToMany
	@OrderBy("genreName")
	@JoinTable(name = "MOVIE_GENRE_LINK", joinColumns = { @JoinColumn(name = "MOVIE_ID") }, inverseJoinColumns = { @JoinColumn(name = "GENRE_ID") })
	private Set<Genre> genres = new HashSet<Genre>();

	public Long getMovieID() {
		return movieID;
	}

	public void setMovieID(Long movieID) {
		this.movieID = movieID;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDisplayRunTime() {
		return displayRunTime;
	}

	public void setDisplayRunTime(String displayRunTime) {
		this.displayRunTime = displayRunTime;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getActorsDisplay() {
		return actorsDisplay;
	}

	public void setActorsDisplay(String actorsDisplay) {
		this.actorsDisplay = actorsDisplay;
	}

	public String getDirectorsDisplay() {
		return directorsDisplay;
	}

	public void setDirectorsDisplay(String directorsDisplay) {
		this.directorsDisplay = directorsDisplay;
	}

	public String getProducersDisplay() {
		return producersDisplay;
	}

	public void setProducersDisplay(String producersDisplay) {
		this.producersDisplay = producersDisplay;
	}

	public String getPosterFileName() {
		return posterFileName;
	}

	public void setPosterFileName(String posterFileName) {
		this.posterFileName = posterFileName;
	}

	public String getStudioDisplay() {
		return studioDisplay;
	}

	public void setStudioDisplay(String studioDisplay) {
		this.studioDisplay = studioDisplay;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

}
