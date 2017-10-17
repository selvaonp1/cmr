package com.telstra.cloud.gateway.cmr.contentrating.client;

import java.io.Serializable;

public class ContentRating implements Serializable {
	
	private String contentType;
	private String contentId;
	private float rating;
	
	public ContentRating() {
	}
	
	public ContentRating(String contentType, String contentId, float rating) {
		this.contentType = contentType;
		this.contentId = contentId;
		this.rating = rating;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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
		return "ContentRating [contentType=" + this.contentType + ", contentId=" + this.contentId + " " + this.rating + "]";
	}
}
