package com.cmr.cloud.content.service;

import com.cmr.cloud.content.domain.ContentRating;
import com.cmr.cloud.content.domain.ContentType;

import java.util.List;

public interface ContentRatingService {

	public List<ContentRating> getRatingsForContent(ContentRatingCriteria criteria);
    public ContentRating save(ContentRating contentRating);
    public List<ContentRating> saveAll(List<ContentRating> contentRatings);
    public List<ContentRating> saveNewOnly(List<ContentRating> contentRatings);
    
}
