package com.cmr.cloud.gateway.cmr.contentrating.client;

import java.util.List;
import java.io.Serializable;

public class ContentRatingCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	private String contentType;
	private List<String> contentIds;
	
	public ContentRatingCriteria() {
		
	}
	
	public ContentRatingCriteria(String contentType, List<String> contentIds) {
		this.contentType = contentType;
		this.contentIds = contentIds;
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
