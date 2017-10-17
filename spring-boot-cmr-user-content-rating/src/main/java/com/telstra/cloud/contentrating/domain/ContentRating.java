package com.cmr.cloud.contentrating.domain;

import java.io.Serializable;


public class ContentRating implements Serializable {
	
	private String userName;
	private String contentType;
	private String contentId;
	private float rating;
	
	public ContentRating() {
		
	}
	
	public ContentRating(String userName,String contentType,  String contentId, float rating) {
		this.userName = userName;
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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		ContentRating contentRating = (ContentRating) obj;
		if ((contentId != null ? !contentId.equals(contentRating.getContentId()) : contentRating.getContentId() != null)
				|| (rating != contentRating.getRating())
				|| (contentType != null ? !contentType.equals(contentRating.getContentType()) : contentRating.getContentType() != null)
				|| (userName != null ? !userName.equals(contentRating.getUserName()) : contentRating.getUserName() != null)
				) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(contentId);
	}

	@Override
	public String toString() {
		return "ContentRating [contentType=" + this.contentType + ", userName=" + this.userName + ", contentId=" + this.contentId + " " + this.rating
				+ "]";
	}	

}
