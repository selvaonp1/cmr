package com.telstra.cloud.content.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telstra.cloud.content.domain.ContentRating;
@Entity
@Table(name = "CognizantMovieRating")
public class ContentRatingModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String contentType;
	private String contentId;
	private float rating;
	private int ratingCount;
	
	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public ContentRatingModel() {
	}

	public ContentRatingModel(String contentType, String contentId, float rating, int ratingCount ) {
		this.contentType = contentType;
		this.contentId = contentId;
		this.rating = rating;
		this.ratingCount = ratingCount;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contenttype) {
		this.contentType = contenttype;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentid(String contentId) {
		this.contentId = contentId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		ContentRating rating = (ContentRating) obj;
		if (contentId != null ? !contentId.equals(rating.getContentId()) : rating.getContentId() != null) {
			return false;
		} else {
			return true;
		}
	}	
	
	@Override
	public String toString() {
		return "ContentRating [contentType=" + this.contentType + ", contentId=" + this.contentId + " " + this.rating +  " count= "+ this.ratingCount + " ID=" + id +  "]";
	}	

}
