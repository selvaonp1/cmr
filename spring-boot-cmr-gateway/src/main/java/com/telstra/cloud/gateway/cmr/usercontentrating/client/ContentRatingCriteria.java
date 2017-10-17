package com.cmr.cloud.gateway.cmr.usercontentrating.client;

import java.util.List;
import java.io.Serializable;


public class ContentRatingCriteria implements Serializable {
    
	private String userName;
	private String contentType;
	private List contentIds;
	
	public ContentRatingCriteria() {
		
	}
	
	public ContentRatingCriteria(String userName, String contentType, List contentIds) {
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
	public List getContentIds() {
		return contentIds;
	}
	public void setContentIds(List contentIds) {
		this.contentIds = contentIds;
	}
}

