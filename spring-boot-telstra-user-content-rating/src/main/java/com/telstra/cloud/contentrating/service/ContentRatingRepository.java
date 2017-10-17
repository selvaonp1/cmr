package com.telstra.cloud.contentrating.service;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

import com.telstra.cloud.contentrating.domain.ContentRating;
import com.telstra.cloud.contentrating.domain.ContentType;
import com.telstra.cloud.contentrating.model.UserContentRatingModel;

public interface ContentRatingRepository extends CrudRepository<UserContentRatingModel, Long> {

	List<UserContentRatingModel> getUserRatingsByUserNameAndContentTypeAndContentIdIn(String userName, String contentType, List contentIds);
	public UserContentRatingModel save(UserContentRatingModel contentRating);
}
