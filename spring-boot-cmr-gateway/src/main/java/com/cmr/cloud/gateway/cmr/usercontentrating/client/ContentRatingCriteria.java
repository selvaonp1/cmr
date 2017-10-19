package com.cmr.cloud.gateway.cmr.usercontentrating.client;

import java.util.List;
import java.io.Serializable;


public class ContentRatingCriteria implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private String userName;
	private String contentType;
	private List<String> contentIds;
	
	public ContentRatingCriteria() {
		
	}
	
	public ContentRatingCriteria(String userName, String contentType, List<String> contentIds) {
		this.userName = userName;
		this.contentType = contentType;
		this.contentIds = contentIds;
	}	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public List<String> getContentIds() {
		return contentIds;
	}
	public void setContentIds(List<String> contentIds) {
		this.contentIds = contentIds;
	}
}

