package com.telstra.cloud.content.domain;

import java.util.List;
import com.telstra.cloud.content.domain.ContentRating;

public class ContentRatingWrapper {
	private List<ContentRating> contentRatings;
	public ContentRatingWrapper() {
	}
	public ContentRatingWrapper(List<ContentRating> contentRatings) {
		this.contentRatings = contentRatings;
	}	
	public List<ContentRating> getContentRatings() {
		return contentRatings;
	}
	public void setContentRatings(List<ContentRating> contentRatings) {
		this.contentRatings = contentRatings;
	}
	
	
}
