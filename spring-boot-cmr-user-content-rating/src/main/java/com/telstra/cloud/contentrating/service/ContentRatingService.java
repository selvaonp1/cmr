package com.cmr.cloud.contentrating.service;

import java.util.List;

import com.cmr.cloud.contentrating.domain.ContentRating;
import com.cmr.cloud.contentrating.domain.ContentType;

public interface ContentRatingService {
	
    public List<ContentRating> getUserRatingsForContent(ContentRatingCriteria criteria);
    public ContentRating save(ContentRating contentRating) throws DuplicateRatingException;
}
