package com.telstra.cloud.content.service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telstra.cloud.content.domain.ContentRating;
import com.telstra.cloud.content.domain.ContentType;
import com.telstra.cloud.content.model.ContentRatingModel;
import com.telstra.cloud.content.service.delegate.ContentRatingServiceDelegate;

import org.springframework.stereotype.Component;

@Component("userService")
public class ContentRatingServiceImpl implements ContentRatingService {

	private final ContentRatingRepository ratingsDao;
	private final ContentRatingServiceDelegate serviceDelegate;
	
	public ContentRatingServiceImpl(ContentRatingRepository ratingsDao, ContentRatingServiceDelegate serviceDelegate) {
		this.ratingsDao = ratingsDao;
		this.serviceDelegate = serviceDelegate;
	}

	@Override
	public List<ContentRating> getRatingsForContent(ContentRatingCriteria criteria) {
		return this.serviceDelegate.getContentRatings(criteria);
	}


	@Override
	public ContentRating save(ContentRating contentRating) {
		List<ContentRating> contentRatingList = new ArrayList<ContentRating>();
		contentRatingList.add(contentRating);
		this.saveAll(contentRatingList);
		return contentRating;
	}

	@Override
	public List<ContentRating> saveAll(List<ContentRating> contentRatings) {
		List<ContentRatingModel> ratingsModelList =  this.serviceDelegate.computeAverageContentRating(contentRatings);
		ratingsDao.saveAll(ratingsModelList);
		return contentRatings;
	}

	@Override
	public List<ContentRating> saveNewOnly(List<ContentRating> contentRatings) {
		List<ContentRatingModel> ratingsModelList =  this.serviceDelegate.addNewContentRatingOnly(contentRatings);
		ratingsDao.saveAll(ratingsModelList);
		return contentRatings;
	}	
}
