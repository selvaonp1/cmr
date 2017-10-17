package com.telstra.cloud.contentrating.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telstra.cloud.contentrating.domain.ContentRating;
import com.telstra.cloud.contentrating.domain.ContentType;
import com.telstra.cloud.contentrating.model.UserContentRatingModel;

import org.springframework.stereotype.Component;

@Component("userService")
public class ContentRatingServiceImpl implements ContentRatingService {

	private final ContentRatingRepository ratingsDao;

	public ContentRatingServiceImpl(ContentRatingRepository ratingsDao) {
		this.ratingsDao = ratingsDao;
	}

	@Override
	public List<ContentRating> getUserRatingsForContent(ContentRatingCriteria criteria) {
		List<ContentRating> ratingsList = new ArrayList<ContentRating>();
		List<UserContentRatingModel> ratingsModelList = ratingsDao.getUserRatingsByUserNameAndContentTypeAndContentIdIn(
				criteria.getUserName(), criteria.getContentType(), criteria.getContentIds());
		if (ratingsModelList != null && ratingsModelList.size() > 0) {
			ratingsList = ratingsModelList.stream()
					.map(p -> new ContentRating(p.getUserName(), p.getContentType(), p.getContentId(), p.getRating()))
					.collect(Collectors.toList());
		}
		return ratingsList;
	}

	@Override
	public ContentRating save(ContentRating contentRating) throws DuplicateRatingException {
		String[] contentIds = new String[] { contentRating.getContentId() };
		List<UserContentRatingModel> ratingsModelList = ratingsDao.getUserRatingsByUserNameAndContentTypeAndContentIdIn(
				contentRating.getUserName(), contentRating.getContentType(), Arrays.asList(contentIds));
		if (ratingsModelList == null || ratingsModelList.size() == 0) {
			UserContentRatingModel ratingModel = new UserContentRatingModel(contentRating.getUserName(),
					contentRating.getContentType(), contentRating.getContentId(), contentRating.getRating());
			ratingsDao.save(ratingModel);
			return contentRating;
		} else {
			throw new DuplicateRatingException(
					new StringBuilder("Rating for ").append(contentRating.getContentId()).append(" exists").toString());
		}
	}

}
