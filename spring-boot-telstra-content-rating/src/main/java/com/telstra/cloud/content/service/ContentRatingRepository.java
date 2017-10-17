package com.telstra.cloud.content.service;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.telstra.cloud.content.model.ContentRatingModel;
import com.telstra.cloud.content.domain.ContentRating;
import com.telstra.cloud.content.domain.ContentType;

public interface ContentRatingRepository extends CrudRepository<ContentRatingModel, Long> {

	List<ContentRatingModel> getRatingsByContentTypeAndContentIdIn(String contentType, List contentIds);
	public ContentRatingModel save(ContentRatingModel contentRating);
	public List<ContentRatingModel> save(List<ContentRatingModel> contentRatings);
}
