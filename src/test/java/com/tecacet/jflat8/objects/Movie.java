package com.tecacet.jflat8.objects;

import java.time.LocalDate;

public class Movie {

	private int id;
	private String name;
	private LocalDate releaseDate;
	private LocalDate videoReleaseDate;
	private String imdbURL;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public LocalDate getVideoReleaseDate() {
		return videoReleaseDate;
	}

	public String getImdbURL() {
		return imdbURL;
	}

}
