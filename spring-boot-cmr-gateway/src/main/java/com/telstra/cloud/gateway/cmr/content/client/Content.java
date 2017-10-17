package com.cmr.cloud.gateway.cmr.content.client;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Content implements Serializable {

	private String contentType;
	private String unit;
	@JsonProperty("show_id")
	private String showId;
	@JsonProperty("mediatype")
	private int mediaType;
	@JsonProperty("show_title")
	private String showTitle;
	@JsonProperty("release_year")
	private String releaseYear;
	private float rating;
	private String category;
	@JsonProperty("show_cast")
	private String showCast;
	private String director;
	private String summary;
	private String poster;
	private String runtime;
	private float myRating;

	public Content() {
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public int getMediaType() {
		return mediaType;
	}

	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getShowCast() {
		return showCast;
	}

	public void setShowCast(String showCast) {
		this.showCast = showCast;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}


	public float getMyRating() {
		return myRating;
	}

	public void setMyRating(float myRating) {
		this.myRating = myRating;
	}

}
