package com.cmr.cloud.contentrating.domain;

public enum ContentType {
	NETFLIXMOVIES("Netflix Roulette");
	private String contentType;
	
	ContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String contentType() {
		return contentType;
	}

}
