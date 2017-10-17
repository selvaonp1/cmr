package com.cmr.cloud.content.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Content implements Serializable {
	
	private ContentType contentType;
	private long unit;
	@JsonProperty("show_id")
	private long showId;
	@JsonProperty("mediatype")
	private int mediaType;
	@JsonProperty("show_title")
	private String showTitle;
	@JsonProperty("release_year")
	private String releaseYear;
	private String rating;
	private String category;
	@JsonProperty("show_cast")
	private String showCast;
	private String director;
	private String summary;
	private String poster;
	private String runtime;
	
	public Content() {
		
	}

	public long getUnit() {
		return unit;
	}
	public void setUnit(long unit) {
		this.unit = unit;
	}
	public long getShowId() {
		return showId;
	}
	public void setShowId(long showId) {
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
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
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
	public Content(ContentType contentType) {
		this.contentType = contentType;
	}
	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
	

}
