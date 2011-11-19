package com.cit.eugene.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "GENRE")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GENRE_ID")
	private Long genreID;

	@Column(name = "GENRE_NAME")
	@NotNull
	private String genreName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MOVIE_GENRE_LINK", joinColumns = { @JoinColumn(name = "GENRE_ID") }, inverseJoinColumns = { @JoinColumn(name = "MOVIE_ID") })
	private Set<Movie> movies = new HashSet<Movie>();

	// To stop JSON going nuts on recursion
	@JsonIgnore
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public Long getGenreID() {
		return genreID;
	}

	public void setGenreID(Long genreID) {
		this.genreID = genreID;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
