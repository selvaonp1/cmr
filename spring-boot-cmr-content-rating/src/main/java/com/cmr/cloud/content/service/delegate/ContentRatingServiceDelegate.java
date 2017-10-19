package com.cmr.cloud.content.service.delegate;

import java.util.List;
import java.util.stream.Collectors;
import com.cmr.cloud.content.domain.ContentRating;
import com.cmr.cloud.content.domain.ContentType;
import com.cmr.cloud.content.model.ContentRatingModel;
import com.cmr.cloud.content.service.ContentRatingCriteria;

public interface ContentRatingServiceDelegate {
	public List<ContentRatingModel> computeAverageContentRating(List<ContentRating> contentRatings);
	public List<ContentRatingModel> addNewContentRatingOnly(List<ContentRating> contentRatings);
	public List<ContentRating> getContentRatings(ContentRatingCriteria criteria);
}
