package com.telstra.cloud.content.service.delegate;

import java.util.List;
import java.util.stream.Collectors;
import com.telstra.cloud.content.domain.ContentRating;
import com.telstra.cloud.content.domain.ContentType;
import com.telstra.cloud.content.model.ContentRatingModel;
import com.telstra.cloud.content.service.ContentRatingCriteria;

public interface ContentRatingServiceDelegate {
	public List<ContentRatingModel> computeAverageContentRating(List<ContentRating> contentRatings);
	public List<ContentRatingModel> addNewContentRatingOnly(List<ContentRating> contentRatings);
	public List<ContentRating> getContentRatings(ContentRatingCriteria criteria);
}
