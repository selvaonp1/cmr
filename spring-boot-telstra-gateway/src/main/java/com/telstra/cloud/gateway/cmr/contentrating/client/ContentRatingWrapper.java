package com.telstra.cloud.gateway.cmr.contentrating.client;

import java.util.List;

public class ContentRatingWrapper {
	private List<ContentRating> contentRatings;

	public ContentRatingWrapper(List<ContentRating> contentRatings) {
		this.contentRatings = contentRatings;
	}
	public ContentRatingWrapper() {
	}
	public List<ContentRating> getContentRatings() {
		return contentRatings;
	}
	public void setContentRatings(List<ContentRating> contentRatings) {
		this.contentRatings = contentRatings;
	}
	
	
}
