package com.cmr.cloud.content.service.delegate;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cmr.cloud.content.domain.ContentRating;
import com.cmr.cloud.content.domain.ContentType;
import com.cmr.cloud.content.model.ContentRatingModel;
import com.cmr.cloud.content.service.ContentRatingCriteria;
import com.cmr.cloud.content.service.ContentRatingRepository;

@Component("contentRatingServiceDelegateImpl")
public class ContentRatingServiceDelegateImpl implements ContentRatingServiceDelegate {

	private final ContentRatingRepository ratingsDao;

	public ContentRatingServiceDelegateImpl(ContentRatingRepository ratingsDao) {
		this.ratingsDao = ratingsDao;
	}

	public List<ContentRatingModel> computeAverageContentRating(List<ContentRating> contentRatings) {
		// get existing records for the provided content
		List<String> contentIds = contentRatings.stream().map(ContentRating::getContentId).collect(Collectors.toList());

		ContentRatingCriteria criteria = new ContentRatingCriteria(ContentType.NETFLIXMOVIES.contentType(), contentIds);
		// Get all existing content ratings
		List<ContentRatingModel> ratingsModelList = ratingsDao
				.getRatingsByContentTypeAndContentIdIn(criteria.getContentType(), criteria.getContentIds());
		List<ContentRatingModel> updatedAverageRatingsModelList = new ArrayList<ContentRatingModel>();
		contentRatings.forEach(rating -> {
			ContentRatingModel ratingModel = ratingsModelList.stream().filter(p -> {
				if (rating.getContentId().equals(p.getContentId())) {
					//updatedAverageRatingsModelList.add(computeAverageRatingsForContent(rating, p));
					computeAverageRatingsForContent(rating, p);
					return true;
				}
				return false;
			}).findAny().orElse(
					new ContentRatingModel(rating.getContentType(), rating.getContentId(), rating.getRating(), 1));
			updatedAverageRatingsModelList.add(ratingModel);
		});
		return updatedAverageRatingsModelList;
	}
	
	public List<ContentRatingModel> addNewContentRatingOnly(List<ContentRating> contentRatings) {
		// get existing records for the provided content
		List<String> contentIds = contentRatings.stream().map(ContentRating::getContentId).collect(Collectors.toList());
		
		ContentRatingCriteria criteria = new ContentRatingCriteria(ContentType.NETFLIXMOVIES.contentType(), contentIds);
		// Get all existing content ratings
		List<ContentRatingModel> ratingsModelList = ratingsDao
				.getRatingsByContentTypeAndContentIdIn(criteria.getContentType(), criteria.getContentIds());
		
		List<String> existingContentIds = ratingsModelList.stream().map(ContentRatingModel::getContentId).collect(Collectors.toList());
		
		boolean newRatings = contentIds.removeAll(existingContentIds);
		List<ContentRatingModel> updatedAverageRatingsModelList = new ArrayList<ContentRatingModel>();
		contentRatings.forEach(rating -> {
			if(contentIds.contains(rating.getContentId())) {
				updatedAverageRatingsModelList.add(new ContentRatingModel(rating.getContentType(), rating.getContentId(), rating.getRating(), 1));
			}
		});			

		return updatedAverageRatingsModelList;
	}	

	public List<ContentRating> getContentRatings(ContentRatingCriteria criteria) {
		List<ContentRating> ratingsList = null;
		List<ContentRatingModel> ratingsModelList = ratingsDao
				.getRatingsByContentTypeAndContentIdIn(criteria.getContentType(), criteria.getContentIds());
		if (ratingsModelList != null) {
			ratingsList = ratingsModelList.stream()
					.map(p -> new ContentRating(p.getContentType(), p.getContentId(), p.getRating()))
					.collect(Collectors.toList());
		}
		return ratingsList;
	}

	private ContentRatingModel computeAverageRatingsForContent(ContentRating newRatingObj,
			ContentRatingModel existingRatingObj) {
		float newRating = newRatingObj.getRating();
		float existingRating = existingRatingObj.getRating();
		int ratingCount = existingRatingObj.getRatingCount();
		int newRatingCount = ratingCount + 1;
		float averageRating = ((existingRating * ratingCount) / newRatingCount) + (newRating / newRatingCount);
		existingRatingObj.setRating(averageRating);
		averageRating = Math.round(averageRating);
		existingRatingObj.setRatingCount(newRatingCount);
		return existingRatingObj;
	}
}
