package com.cmr.cloud.content.service;

import java.io.Serializable;

import org.springframework.util.Assert;

public class ContentSearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	private String director;
	
	public ContentSearchCriteria(String director) {
		Assert.notNull(director, "Director name must not be null");
		this.director = director;
	}	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	

}
