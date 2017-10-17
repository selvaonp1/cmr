package com.cmr.cloud.contentrating.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.cmr.cloud.content.model.ContentRatingModel;
import com.cmr.cloud.content.service.ContentRatingRepository;
import com.cmr.cloud.content.domain.ContentType;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link ContentRatingRepository}.
 *
 * @author Selva ONP
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//Separate profile for web tests to avoid using dev databases
@ActiveProfiles("test")
public class ContentRatingsRepositoryTests {

	@Autowired
	ContentRatingRepository repository;

	@Test
	public void getRatingsByContentTypeAndContentIdIn() {
		String contentIds[] = new String[] {"880640","60010514"};
		
		List<ContentRatingModel> ratings = this.repository.getRatingsByContentTypeAndContentIdIn(
				ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		assertThat(ratings.size()).isEqualTo(2);
		
	}
	
	@Test
	public void saveContentRating() {
		ContentRatingModel userRating = new ContentRatingModel(ContentType.NETFLIXMOVIES.contentType(), "902003", 4.0f, 1);
		this.repository.save(userRating);
		
		String contentIds[] = new String[] {"902003"};
		List<ContentRatingModel> ratings = this.repository.getRatingsByContentTypeAndContentIdIn(
				ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		assertThat(ratings.size()).isEqualTo(1);
		
	}	

	@Test
	public void saveAllContentRating() {
		List<ContentRatingModel> ratingsModelList = new ArrayList<ContentRatingModel>();
		ratingsModelList.add( new ContentRatingModel(ContentType.NETFLIXMOVIES.contentType(), "902004", 3.7f, 1));
		ratingsModelList.add( new ContentRatingModel(ContentType.NETFLIXMOVIES.contentType(), "902005", 3.7f, 1));
		this.repository.saveAll(ratingsModelList);
		
		String contentIds[] = new String[] {"902004", "902005"};
		List<ContentRatingModel> ratings = this.repository.getRatingsByContentTypeAndContentIdIn(
				ContentType.NETFLIXMOVIES.contentType(), Arrays.asList(contentIds));
		assertThat(ratings.size()).isEqualTo(2);
		
	}	
	
}
