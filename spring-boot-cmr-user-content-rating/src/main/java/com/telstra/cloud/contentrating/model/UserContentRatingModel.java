package com.cmr.cloud.contentrating.model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.cmr.cloud.contentrating.domain.ContentRating;
@Entity
@Table(name = "UserMovieRating")
public class UserContentRatingModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String userName;
	private String contentType;
	private String contentId;
	private float rating;
	
	public UserContentRatingModel() {
	}

	public UserContentRatingModel(String userName, String contentType, String contentId, float rating) {
		this.userName = userName;
		this.contentType = contentType;
		this.contentId = contentId;
		this.rating = rating;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentid(String contentid) {
		this.contentId = contentid;
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
		return "UserContentRating [contentType=" + this.contentType + ", userName=" + this.userName + ", contentId=" + this.contentId + " " + this.rating
				+ "]";
	}	
	
}
