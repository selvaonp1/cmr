package com.cmr.cloud.gateway.cmr.usercontentrating.client;

import java.io.Serializable;

public class UserContentRating implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String contentType;
	private String userName;
	private String contentId;
	private float rating;
	
	public UserContentRating() {
		
	}
	
	public UserContentRating(String contentType, String userName, String contentId, float rating) {
		this.contentType = contentType;
		this.userName = userName;
		this.contentId = contentId;
		this.rating = rating;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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

	public void setContentId(String contentid) {
		this.contentId = contentid;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		return "UserContentRating [userName=" + this.userName + "contentType=" + this.contentType + ", contentId=" + this.contentId + " " + this.rating + "]";
	}
}
