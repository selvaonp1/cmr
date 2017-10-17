package com.cmr.cloud.gateway.cmr.contentrating.client;

import java.util.List;
import java.io.Serializable;

public class ContentRatingCriteria implements Serializable {
    
	private String contentType;
	private List contentIds;
	
	public ContentRatingCriteria() {
		
	}
	
	public ContentRatingCriteria(String contentType, List contentIds) {
		this.contentType = contentType;
		this.contentIds = contentIds;
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
